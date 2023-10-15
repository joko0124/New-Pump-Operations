<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use DB;

class PumpOperationsApiController extends Controller
{
    public function getJoDetails(Request $request)
    {
    	$number = $request->input('jo_number');
    	$query = DB::table('jo')->where('jo_number', $number)->first();
    	
    	return response()->json($query);
    }
}
