<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use DB;

class PumpOperationsApiController extends Controller
{
    public function getJoDetails(Request $request)
    {
    	$number = $request->input('jo_number');
    	# return jo id
    	$id = DB::table('jo')->where('jo_number', $number)->value('id');
    	# get details
    	$query = DB::table('jo')->where('id', $id)->first();
    	
    	# check category
    	switch ($query->category_code) {
    		case 'SAS':
    			return "Service Application Survey";
    			break;
    		
    		case 'RM':
    			return "Repairs and Maintenance";
    			break;

    		default:
    			# code...
    			break;
    	}
    	
    	// return response()->json($query);
    }

    public function BillingStatsSummaryData(Request $req) {
		$month   = $req->input('month');
		$year    = $req->input('year');
		$books   = DB::table('books')->where('branch_id', session('branch_id'))->select('id', 'code', 'description')->orderBy('code', 'ASC')->get();

		# Set headers.
		$header1 = ['CUSTOMERS', 'CONSUMPTION', 'AMOUNT']; 
		$header2 = DB::table('bills_details AS bd')
					 ->join('bills_header AS bh', 'bh.id', 'bd.batch_id')
					 ->where('bh.bill_month', $month)
					 ->where('bh.bill_year', $year)
					 ->orderBy('bd.acct_class', 'ASC')
					 ->groupBy('bd.acct_class')
					 ->pluck('bd.acct_class')->toArray();

		# Check if billing is closed.
		$closed  = DB::table('bills_header')
					 ->where('bill_month', $month)
					 ->where('bill_year', $year)
					 ->whereNull('closed_at')
					 ->count();

		# Add sub-total to every row.
		array_push($header2, "TOTAL");
	
		# Get billing statistics data.
		foreach ($books AS $b) {
			foreach ($header1 AS $h1) {
				switch ($h1) {
					case 'CUSTOMERS':
						foreach ($header2 as $h2) {
							$b->details[$h1][$h2] = DB::table('bills_details AS bd')
													  ->join('bills_header AS bh', 'bh.id', 'bd.batch_id')
													  ->whereIn('bd.bill_type', ['RB', 'AB', 'AL'])
													  ->where('bh.book_id', $b->id)
													  ->where('bh.bill_month', $month)
													  ->where('bh.bill_year', $year)
													  ->where('acct_class', $h2)
													  ->count();
						}

						$b->details[$h1]['TOTAL'] = DB::table('bills_details AS bd')
													  ->join('bills_header AS bh', 'bh.id', 'bd.batch_id')
													  ->whereIn('bd.bill_type', ['RB', 'AB', 'AL'])
													  ->where('bh.book_id', $b->id)
													  ->where('bh.bill_month', $month)
													  ->where('bh.bill_year', $year)
													  ->count();
					break;

					case 'CONSUMPTION':
						# Get all data per book.
						$qr = DB::table('bills_details AS bd')
							  	->join('bills_header AS bh', 'bh.id', 'bd.batch_id')
							  	->whereNotIn('bd.bill_type', ['FB'])
							  	->where('bh.book_id', $b->id)
							  	->where('bh.bill_month', $month)
							  	->where('bh.bill_year', $year)
							  	->select('bd.id', 'bd.bill_type', 'bd.acct_class', 'bd.total_cum')
							  	->get();

						# Check if bill type is AL, if AL get original consumption from bill history.
						foreach ($qr AS $bill) {
							if($bill->bill_type == 'AL') {
								$bill->total_cum = DB::table('bills_details_history')->where('id', $bill->id)->whereIn('bill_type', ['RB', 'AB'])->value('total_cum');
							}
						}

						foreach ($header2 as $h2) {
							$b->details[$h1][$h2] = $qr->where('acct_class', $h2)->sum('total_cum');
						}

						$b->details[$h1]['TOTAL'] = $qr->sum('total_cum');
					break;

					case 'AMOUNT':
						# Get all data per book.
						$qr = DB::table('bills_details AS bd')
							  	->join('bills_header AS bh', 'bh.id', 'bd.batch_id')
							  	->whereNotIn('bd.bill_type', ['FB'])
							  	->where('bh.book_id', $b->id)
							  	->where('bh.bill_month', $month)
							  	->where('bh.bill_year', $year)
							  	->select('bd.id', 'bd.bill_type', 'bd.acct_class', 'bd.bill_amount')
							  	->get();

						# Check if bill type is AL, if AL get original consumption from bill history.
						foreach ($qr AS $bill) {
							if($bill->bill_type == 'AL') {
								$bill->bill_amount = DB::table('bills_details_history')->where('id', $bill->id)->whereIn('bill_type', ['RB', 'AB'])->value('bill_amount');
							}
						}

						foreach ($header2 as $h2) {
							$b->details[$h1][$h2] = $qr->where('acct_class', $h2)->sum('bill_amount');
						}

						$b->details[$h1]['TOTAL'] = $qr->sum('bill_amount');
					break;	
				}
			}
		}

		$response = [
			'year'		=> $year, 
			'month'		=> date("F", mktime(0, 0, 0, $month, 10)),
			'books'		=> $books, 
			'header1'	=> $header1, 
			'header2'	=> $header2
		];

		return response()->json($response);
	}
}
