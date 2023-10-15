@extends('layouts.app')
@section('title', session('company_code') . ' Elite System')

@section('header')
Dashboard
@endsection

@section('body')
<div class="box box-default">
	<div class="box-body">
        <div class="row">
            <div class="col-xs-12">
                <label class="text-primary" style="font-size:16px; margin-top:6px">Job Orders</label>

                <div class="pull-right box-tools">
                    <a href="{{url('csr/job-order/jo-new')}}" class="btn btn-default pull-right"><i class="fa fa-plus-circle text-success"></i> New Job Order</a>
                </div>

                <div class="pull-right" style="width:160px; margin-right:3px">
                    <select id="filter-by-status" class="form-control select2 select2-hidden-accessible" data-placeholder="JO STATUS" style="width: 100%;" aria-hidden="true">
                        <option></option>
                        <option value="pending">PENDING</option>
                        <option value="dispatched">DISPATCHED</option>
                        <option value="accomplished">ACCOMPLISHED</option>
                        <option value="cancelled">CANCELLED</option>
                        <option value="all">ALL</option>
                    </select>
                </div>
                <div class="pull-right" style="width:300px; margin-right:3px">
                    <select id="filter-by-category" class="form-control select2 select2-hidden-accessible" data-placeholder="JO CATEGORY" style="width: 100%;" aria-hidden="true">
                        <option></option>
                        @foreach($jo_categories AS $jc)
                        <option value="{{ $jc->code }}">{{ strtoupper($jc->description) }}</option>
                        @endforeach
                    </select>
                </div>
            </div>
        </div>
        <hr style="margin-top:6px; margin-bottom:8px">
  		<div class="row">
    		<div class="col-xs-12">
                <table class="table table-hover table-condensed table-responsive" id="table-joborders">
                    <thead class="table-header-bg">
                        <tr>
                            <th width="9%">JO NUMBER</th>
                            <th width="32%">JO CATEGORY</th>
                            <th width="24%">CUSTOMER NAME</th>
                            <th width="20%">ACCT NO.</th>
                            <th width="20%">CUST NAME</th>
                            <th width="20%">APPL NAME</th>
                            <th width="24%">ADDRESS</th>
                            <th width="8%">STATUS</th>
                            <th width="3%">&nbsp;</th>
                        </tr>
                    </thead>
                </table>
    		</div>
	  	</div>
	</div>
</div>
@endsection

@section('scripts')
<script type="text/javascript">
    $(document).on('ready', function() {
        $dt = $('#table-joborders').DataTable({
            deferred: true,
            processing: true,
            serverSide: true,
            ajax: {
                url: $app_url + '/csr/job-order/dt',
                data: function(d) {
                    d.jo_category = $('#filter-by-category').val();
                    d.jo_status   = $('#filter-by-status').val();
                }
            },
            columns: [
                { data:'jo_number',         name:'j.jo_number',         orderable:false,   className:"text-uppercase" },
                { data:'jo_category',       name:'jo_category',         orderable:false,   className:"text-uppercase",      searchable:false },
                { data:'customer',          name:'customer',            orderable:false,   className:"text-uppercase" },
                { data:'acct_number',       name:'acct_number',         orderable:false,   className:"text-uppercase" },
                { data:'cust_name',         name:'cust_name',           orderable:false,   className:"text-uppercase" },
                { data:'appl_name',         name:'appl_name',           orderable:false,   className:"text-uppercase" },
                { data:'complete_address',  name:'complete_address',    orderable:false,   className:"text-uppercase" },
                { data:'status',            name:'j.status',            orderable:false,   className:"text-uppercase",      searchable:false },
                { data:'action',            name:'action',              orderable:false,   className:"text-right",          searchable:false },
            ],
            columnDefs: [
                {
                    targets: 3,
                    visible: false
                },
                {
                    targets: 4,
                    visible: false
                },
                {
                    targets: 5,
                    visible: false
                },
                {
                    targets: 6,
                    render: function(data, type, row) {
                        if(data.length > 38) {
                            return data.substr(0,38) + '...';
                        } else {
                            return data;
                        }
                    }
                }
            ],
            createdRow: function(row, data, dataIndex) {
                $(row).attr('data-id', data.id);
            }
        });

        $('#table-joborders_filter input').unbind();
        $('#table-joborders_filter input').bind('keyup', function(e) {
            if(e.keyCode == 13) {
                $dt.search($(this).val()).draw();
            }
        });

        $('#filter-by-category').change(function() {
            $dt.search($('#table-joborders_filter input').val()).draw();
        });

        $('#filter-by-status').change(function() {
            $dt.search($('#table-joborders_filter input').val()).draw();
        });

        $('input[type="search"]').focus();
    })
    .on('click', '.jo-dispatch', function() {
        $tr = $(this).closest('tr');

        $jc = $.confirm({
            draggable: true,
            title: '',
            columnClass: 'col-md-6 col-md-offset-3',
            color: 'orange',
            content: function() {
                var self = this;
                return $.ajax({
                    url: $app_url + '/csr/job-order/jo-dispatch',
                    method: 'get',
                    dataType: 'html',
                }).done(function(response) {
                    self.setContent(response);
                });
            },
            onContentReady: function() {
                $('#dispatch_to').select2({
                    dropdownParent: $('.jconfirm-scrollpane')
                });

                $(".datetime-masked").inputmask("datetime", {
                    mask: "y-2-1 h:s t\\m", 
                    placeholder: "yyyy-mm-dd hh:mm xm", 
                    leapday: "-02-29", 
                    separator: "-", 
                    alias: "yyyy/mm/dd",
                    hourFormat: 12,
                    onincomplete: function() {
                        $(this).val(null);
                    }
                });
            },
            buttons: {
                CANCEL: {
                    btnClass: 'btn-default'
                },
                DISPATCH: {
                    btnClass: 'btn-warning',
                    text: 'DISPATCH JO',
                    action: function() {
                        $.ajax({
                            url: $app_url + '/csr/job-order/jo-dispatch',
                            method: 'post',
                            data: {
                                'jo_id'         : $tr.data('id'),
                                'dispatch_to'   : $('#dispatch_to').val(),
                                'dispatched_at' : $('#dispatched_at').val()
                            },
                            beforeSend: function() {
                                $jc.showLoading(true);
                                $('.form-group').removeClass('has-error');
                            },
                            success: function(jo_number) {
                                $jc.close();
                                $dt.draw();

                                // For Pump Operations 
                                function get_jo_details($jno)
                                {
                                    $.ajax({
                                        url: $app_url + '/pump-operations/get-jo-details',
                                        method: 'get',
                                        data: {
                                            'jo_number' : jo_number,
                                        },
                                        beforeSend: function() {
                                            
                                        },
                                        success: function(response) {
                                            console.log(response)

                                            // Connect p-operations api
                                            $.ajax({
                                                url: 'http://127.0.0.1/p-operations/public/elite-api/get-jo-data',
                                                method: 'get',
                                                data: {
                                                    'data' : response,
                                                },
                                                beforeSend: function() {
                                                    
                                                },
                                                success: function(response) {
                                                    console.log(response)
                                                },
                                                error: function(data) {  
                                                    console.log(data)
                                                },
                                                complete: function() {
                                                    $jc.hideLoading(true);
                                                }
                                            });
                                        },
                                        error: function(data) {  
                                            console.log(data)
                                        },
                                        complete: function() {
                                            $jc.hideLoading(true);
                                        }
                                    });
                                }

                                get_jo_details(jo_number);

                                window.open($app_url+'/csr/job-order/jo-print?jo-number='+response.jo_number, '_blank');
                            },
                            error: function(data) {  
                                switch(data.status) {
                                    case 422:
                                        $.each(data.responseJSON, function(f, m) {
                                            $('#'+f).closest('.form-group').addClass('has-error');
                                        });
                                    break;
                                }
                            },
                            complete: function() {
                                $jc.hideLoading(true);
                            }
                        });

                        return false;
                    }
                }
            }
        });
    })
    .on('click', '.jo-findings', function() {
        $tr = $(this).closest('tr');

        window.location.href = $app_url + '/csr/job-order/findings/' + $tr.data('category').toLowerCase() + '?jid=' + $tr.data('id');
    })
    .on('click', '.jo-print',    function() {
        $tr = $(this).closest('tr');

        if($tr.data('status') != 'dispatched') {
            $.confirm({
                draggable: true,
                type: 'red',
                title: '',
                content: '<p class="text-center"><i class="fa fa-gg text-bold text-danger" style="font-size:54px"></i></p>' +
                        '<p class="text-center">Job order must be dispatched before you can print the JO form.</p>',
                buttons: {
                    yes: {
                        text: 'got it!',
                        btnClass: 'btn-danger'
                    }
                }
            });
        }
    })
    .on('click', '.jo-cancel',  function() {
        $tr = $(this).closest('tr');

        $.confirm({
            draggable: true,
            type: 'red',
            title: false,
            columnClass: 'col-md-6 col-md-offset-3',
            content: function() {
                var self = this;
                return $.ajax({
                    url: $app_url + '/csr/job-order/jo-cancel',
                    method: 'get',
                    dataType: 'html',
                }).done(function(response) {
                    self.setContent(response);
                });
            },
            onContentReady: function() {
                $('#reason').focus();
            },
            buttons: {
                CANCEL: {
                    btnClass: 'btn-default',
                    keys: ['esc']
                },
                CONFIRM: {
                    btnClass: 'btn-danger',
                    text: 'CANCEL JOB-ORDER',
                    keys: ['enter'],
                    action: function() {
                        $btn = this.buttons.CONFIRM;

                        $.ajax({
                            url: $app_url + '/csr/job-order/jo-cancel',
                            method: 'post',
                            data: {
                                'jo_id'  : $tr.data('id'),
                                'reason' : $('#reason').val()
                            },
                            beforeSend: function() {
                                $btn.disable();
                                $btn.setText('SAVING...');

                                $('.form-group').removeClass('has-error');
                            },
                            success: function() {
                                window.location.href = $app_url + '/csr/job-order';
                            },
                            error: function(data) {  
                                switch(data.status) {
                                    case 422:
                                        $.each(data.responseJSON, function(f, m) {
                                            $('#'+f).closest('.form-group').addClass('has-error');
                                        });
                                    break;
                                }
                            },
                            complete: function() {
                                $btn.enable();
                                $btn.setText('CANCEL JOB-ORDER');
                            }
                        });

                        return false;
                    }
                }
            }
        });
    })
    .on('click', '.jo-materials',  function() {
        $joborder_id = $(this).closest('tr').data('id');

        $jc = $.confirm({
            title: false,
            draggable: true,
            columnClass: 'large',
            content: function() {
                var self = this;

                return $.ajax({
                    url: $app_url + '/csr/job-order/jo-materials-additional',
                    dataType: 'html',
                    data: { 'joborder_id' : $joborder_id },
                    method: 'get'
                }).done(function(res) {
                    self.setContent(res);
                }).fail(function(res) {
                    if(JSON.parse(res.responseText).jcon == true) {
                        $.confirm({
                            draggable: true,
                            type: 'red',
                            title: '',
                            content: '<p class="text-center"><i class="fa fa-gg text-bold text-danger" style="font-size:54px"></i></p>' +
                                    '<p class="text-center">'+ JSON.parse(res.responseText).msg +'</p>',
                            buttons: {
                                yes: {
                                    text: '<small>[ENTER]</small> got it!',
                                    btnClass: 'btn-danger',
                                    keys: ['enter', 'esc']
                                }
                            }
                        });

                        $jc.close();
                    } else {
                        self.setContent('Something went wrong. Please try again or refresh the page.');
                    }
                });
            },
            onContentReady: function() {
                function FormatMaterialsSearchResults(results) {
                    if(results.loading) return results.text;

                    $category = results.item_category ? results.item_category.toUpperCase() : 'N/A';
                    $code     = results.item_code ? results.item_code.toUpperCase() : 'N/A';
                    $uom      = results.item_uom ? results.item_uom.toUpperCase() : 'N/A';

                    var markup = "<div class='select2-result-repository clearfix'>" +
                                 "<div class='select2-result-repository__title'>" + results.text + "</div>";

                    markup +=   "<div class='select2-result-repository__statistics'>" +
                                    "<div class='select2-result-repository__forks'><i class='fa fa-tags fa-fw'></i>" + $category + "</div>" +
                                    "<div class='select2-result-repository__stargazers'><i class='fa fa-qrcode fa-fw'></i>" + $code + "</div>" +
                                    "<div class='select2-result-repository__stargazers'><i class='fa fa-sitemap fa-fw'></i>" + $uom + "</div>" +
                                "</div>";

                    return markup;
                }

                function AppendNewMaterialsRow() {
                    $('#tbl-materials > tbody').append('<tr>' +
                        '<td class="form-group" style="padding:2px">' +
                            '<select class="form-control select2 select2-hidden-accessible material-id" data-placeholder="SELECT MATERIALS" style="width: 100%;" aria-hidden="true">' +
                                '<option></option>' +
                            '</select>' +
                        '</td>' +
                        '<td class="form-group" style="padding:2px">' +
                            '<input type="text" class="fees-control form-control material-uom" disabled="true">' +
                        '</td>' +
                        '<td class="form-group" style="padding:2px">' +
                            '<input type="text" class="fees-control form-control text-right material-unit-price curr" data-num-min="1" data-num-max="99999" disabled="true">' +
                        '</td>' +
                        '<td class="form-group" style="padding:2px">' +
                            '<input type="text" class="fees-control form-control material-qty num pnum-only" disabled="true">' +
                        '</td>' +
                        '<td class="form-group" style="padding:2px">' +
                            '<input type="text" class="fees-control form-control text-right material-total-price" disabled="true">' +
                        '</td>' +
                    '</tr>');
                }

                function ComputeGrandtotal() {
                    $total = numeral(0);

                    $.each($('.material-total-price'), function() {
                        $total.add($(this).val().replace(',', ''));
                    });

                    $('#material-grand-total').val($total.format('0,0.00')).data('amt', $total.value());

                    // Check if grand total is greater than zero.
                    if(numeral($('#material-grand-total').data('amt')).value() > 0) {
                        $('#materials_payment_method').attr('disabled', false);
                    } else {
                        $('#materials_payment_method').attr('disabled', true);
                    }
                }

                function InitializeAppendRow() {
                    // Initiliaze appended materials select2.
                    $('.material-id').select2({
                        allowClear: true,
                        dropdownParent: $('.jconfirm-scrollpane'),
                        ajax: {
                            url: $app_url + '/select2-materials-search',
                            delay: 250,
                            data: function (params) {
                                return query = {
                                    search: params.term,
                                    page: params.page
                                }
                            },
                            processResults: function (data, params) {
                                params.page = params.page || 1;

                                return {
                                    results: $.map(data.items, function (data) {
                                        return {
                                            text: data.description,
                                            item_category: data.category,
                                            item_code: data.code,
                                            item_uom: data.uom,
                                            id: data.id
                                        }
                                    }),
                                    pagination: {
                                        more: (params.page * 10) < data.count
                                    }
                                };
                            },

                        },
                        escapeMarkup: function (markup) { return markup; },
                        templateResult: FormatMaterialsSearchResults
                    }).on('select2:select', function (event) {
                        $tr = $(this).closest('tr');

                        $materials = [];
                        $.each($('.material-id'), function() {
                            $materials.push($(this).val());
                        });

                        if(_.uniq($materials).length !== $materials.length) {
                            $(this).val(null).trigger('change');
                        } else {
                            $.get($app_url + '/select2-materials-details', { 'material_id':$(this).val() })
                            .done(function(data) {
                                $tr.find('.material-uom').val(data.uom.toUpperCase());
                                $tr.find('.material-qty').val(1).prop('disabled', false);
                                $tr.find('.material-total-price').val(numeral(1).format('0,0.00')).trigger('change');
                                $tr.find('.material-unit-price').val(numeral(1).format('0,0.00')).prop('disabled', false).focus().select();

                                AppendNewMaterialsRow();
                                InitializeAppendRow();
                            });
                        }
                    }).on('select2:unselect', function (event) {
                        $(this).select2('destroy');
                        $(this).closest('tr').remove();

                        ComputeGrandtotal();
                    });

                    // Compute total price.
                    $('.material-unit-price').on('keyup', function() {
                        $tr = $(this).closest('tr');

                        $qty = $tr.find('.material-qty').val();
                        $uprice = $(this).val().replace(',', '');
                        $tprice = $qty * $uprice;

                        $tr.find('.material-total-price').val(numeral($tprice).format('0,0.00'));

                        ComputeGrandtotal();
                    });

                    $('.material-qty').on('keyup', function() {
                        $tr = $(this).closest('tr');

                        $qty = $(this).val().replace(',', '');
                        $uprice = $tr.find('.material-unit-price').val().replace(',', '');
                        $tprice = $qty * $uprice;

                        $tr.find('.material-total-price').val(numeral($tprice).format('0,0.00'));
                        
                        ComputeGrandtotal();
                    });

                    // Compute grand total.
                    ComputeGrandtotal();
                }

                $('#materials_payment_method').select2({
                    allowClear: true,
                    dropdownParent: $('.jconfirm-scrollpane'),
                });

                AppendNewMaterialsRow();
                InitializeAppendRow();

                $('#materials_payment_method').on('change', function() {
                    $pm = $(this).val();

                    if($pm == 'atb') {
                        $grand_total = numeral($('#material-grand-total').data('amt'));
                        $terms = numeral(1);
                        $amortization = $grand_total.divide($terms.value());

                        $('#materials_terms').val($terms.value()).attr('disabled', false);
                        $('#materials_amortization').val($amortization.format('0,0.00'));
                    } else {
                        $('#materials_terms').val(null).attr('disabled', true);
                        $('#materials_amortization').val(null);
                    }
                });

                $('#materials_terms').on('keyup change', function() {
                    $grand_total = numeral($('#material-grand-total').data('amt'));
                    $terms = numeral($(this).val());
                    $amortization = $grand_total.divide($terms.value());

                    $('#materials_terms').val($terms.value());
                    $('#materials_amortization').val($amortization.format('0,0.00'));
                });              
            },
            buttons: {
                CANCEL: {
                    btnClass:'btn btn-default',
                    text: '<small>[ESC]</small> CANCEL',
                    keys: ['esc']
                },
                CONFIRM: {
                    btnClass: 'btn btn-primary',
                    text: '<i class="fa fa-save"></i> SAVE CHANGES',
                    action: function() {
                        $jc.showLoading(true);

                        // Save details in array.
                        $materials = [];
                        $.each($('.material-id'), function() {
                            if($(this).val()) {
                                $tr = $(this).closest('tr');

                                $materials.push({
                                    'id' : $(this).val(),
                                    'text' : $(this).text(),
                                    'uom' : $tr.find('.material-uom').val(),
                                    'unit_price' : $tr.find('.material-unit-price').val().replace(',', ''),
                                    'qty' : $tr.find('.material-qty').val(),
                                    'total_price' : $tr.find('.material-total-price').val().replace(',', '')
                                });
                            }
                        });

                        // Save additional materials.
                        $.ajax({
                            url: $app_url + '/csr/job-order/jo-materials-additional-save',
                            type: 'post',
                            data: {
                                'joborder_id'               : $joborder_id,
                                'materials_price_total'     : $('#material-grand-total').data('amt') ? $('#material-grand-total').data('amt') : 0,
                                'materials_payment_method'  : $('#materials_payment_method').val(),
                                'materials_terms'           : Number($('#materials_terms').val()),
                                'materials_details'         : $materials,
                            },
                            success: function(data) {
                                $.confirm({
                                    draggable: true,
                                    title: false,
                                    type: 'blue',
                                    content: '<p class="text-center"><i class="fa fa-fw fa-check text-bold text-primary" style="font-size:54px"></i></p>' +
                                            '<p class="text-center">Additional materials was successfully added, please proceed to teller for the payment.</p>',
                                    buttons: {
                                        yes: {
                                            text: '<small>[ENTER]</small> got it!',
                                            btnClass: 'btn-primary',
                                            keys: ['enter']
                                        }
                                    }
                                });

                                $jc.close();
                                $dt.search($('#table-joborders_filter input').val()).draw();
                            },
                            error: function(result) {
                                $.each(result.responseJSON, function(field, message) {
                                    $('#'+field).parent().addClass('has-error').prop('title', message);
                                });

                                $jc.hideLoading(true);
                            }
                        });

                        return false;
                    }
                }
            }
        });
    })
    .on('click', '.jo-services',  function() {
        $joborder_id = $(this).closest('tr').data('id');

        $jc = $.confirm({
            title: false,
            draggable: true,
            columnClass: 'large',
            content: function() {
                var self = this;

                return $.ajax({
                    url: $app_url + '/csr/job-order/jo-services-additional',
                    dataType: 'html',
                    data: { 'joborder_id' : $joborder_id },
                    method: 'get'
                }).done(function(res) {
                    self.setContent(res);
                }).fail(function(res) {
                    if(JSON.parse(res.responseText).jcon == true) {
                        $.confirm({
                            draggable: true,
                            type: 'red',
                            title: '',
                            content: '<p class="text-center"><i class="fa fa-gg text-bold text-danger" style="font-size:54px"></i></p>' +
                                    '<p class="text-center">'+ JSON.parse(res.responseText).msg +'</p>',
                            buttons: {
                                yes: {
                                    text: '<small>[ENTER]</small> got it!',
                                    btnClass: 'btn-danger',
                                    keys: ['enter', 'esc']
                                }
                            }
                        });

                        $jc.close();
                    } else {
                        self.setContent('Something went wrong. Please try again or refresh the page.');
                    }
                });
            },
            onContentReady: function() {
                function FormatMaterialsSearchResults(results) {
                    if(results.loading) return results.text;

                    var markup = "<div class='select2-result-repository clearfix'>" +
                                 "<div class='select2-result-repository__title'>" + results.text + "</div>";

                    return markup;
                }

                function AppendNewMaterialsRow() {
                    $('#tbl-services > tbody').append('<tr>' +
                        '<td class="form-group" style="padding:2px">' +
                            '<select class="form-control select2 select2-hidden-accessible service-id" data-placeholder="SELECT SERVICE" style="width: 100%;" aria-hidden="true">' +
                                '<option></option>' +
                            '</select>' +
                        '</td>' +
                        '<td class="form-group" style="padding:2px">' +
                            '<input type="text" class="fees-control form-control text-right curr service-amount" data-num-min="1" data-num-max="9999999">' +
                        '</td>');
                }

                function ComputeGrandtotal() {
                    $total = numeral(0);

                    $.each($('.service-amount'), function() {
                        $total.add($(this).val().replace(',', ''));
                    });

                    $('#services-grand-total').val($total.format('0,0.00')).data('amt', $total.value());

                    // Check if grand total is greater than zero.
                    if(numeral($('#services-grand-total').data('amt')).value() > 0) {
                        $('#services_payment_method').attr('disabled', false);
                    } else {
                        $('#services_payment_method').attr('disabled', true);
                    }
                }

                function InitializeAppendRow() {
                    // Initiliaze appended materials select2.
                    $('.service-id').select2({
                        allowClear: true,
                        dropdownParent: $('.jconfirm-scrollpane'),
                        ajax: {
                            url: $app_url + '/select2-services-search',
                            delay: 250,
                            data: function (params) {
                                return query = {
                                    search: params.term,
                                    page: params.page
                                }
                            },
                            processResults: function (data, params) {
                                params.page = params.page || 1;

                                return {
                                    results: $.map(data.items, function (data) {
                                        return {
                                            id: data.id,
                                            text: data.description.toUpperCase()
                                        }
                                    }),
                                    pagination: {
                                        more: (params.page * 10) < data.count
                                    }
                                };
                            },

                        },
                        escapeMarkup: function (markup) { return markup; },
                        templateResult: FormatMaterialsSearchResults
                    }).on('select2:select', function (event) {
                        $tr = $(this).closest('tr');

                        $services = [];
                        $.each($('.service-id'), function() {
                            $services.push($(this).val());
                        });

                        if(_.uniq($services).length !== $services.length) {
                            $(this).val(null).trigger('change');
                        } else {
                            $.get($app_url + '/select2-services-details', { 'service_id':$(this).val() })
                            .done(function(data) {
                                $tr.find('.service-amount').val(numeral(data.amount).format('0,0.00'));

                                AppendNewMaterialsRow();
                                InitializeAppendRow();
                            });
                        }
                    }).on('select2:unselect', function (event) {
                        $(this).select2('destroy');
                        $(this).closest('tr').remove();

                        ComputeGrandtotal();
                    });

                    // Compute grand total.
                    $('.service-amount').on('keyup', function() {                        
                        ComputeGrandtotal();
                    });

                    // Compute grand total.
                    ComputeGrandtotal();
                }

                $('#services_payment_method').select2({
                    allowClear: true,
                    dropdownParent: $('.jconfirm-scrollpane'),
                });

                AppendNewMaterialsRow();
                InitializeAppendRow();

                $('#services_payment_method').on('change', function() {
                    $pm = $(this).val();

                    if($pm == 'atb') {
                        $grand_total = numeral($('#services-grand-total').data('amt'));
                        $terms = numeral(1);
                        $amortization = $grand_total.divide($terms.value());

                        $('#services_terms').val($terms.value()).attr('disabled', false);
                        $('#services_amortization').val($amortization.format('0,0.00'));
                    } else {
                        $('#services_terms').val(null).attr('disabled', true);
                        $('#services_amortization').val(null);
                    }
                });

                $('#services_terms').on('keyup change', function() {
                    $grand_total = numeral($('#services-grand-total').data('amt'));
                    $terms = numeral($(this).val());
                    $amortization = $grand_total.divide($terms.value());

                    $('#services_terms').val($terms.value());
                    $('#services_amortization').val($amortization.format('0,0.00'));
                });              
            },
            buttons: {
                CANCEL: {
                    btnClass:'btn btn-default',
                    text: '<small>[ESC]</small> CANCEL',
                    keys: ['esc']
                },
                CONFIRM: {
                    btnClass: 'btn btn-primary',
                    text: '<i class="fa fa-save"></i> SAVE CHANGES',
                    action: function() {
                        $jc.showLoading(true);

                        // Save details in array.
                        $services = [];
                        $.each($('.service-id'), function() {
                            if($(this).val()) {
                                $tr = $(this).closest('tr');

                                $services.push({
                                    'id' : $(this).val(),
                                    'description' : $(this).text(),
                                    'amount' : $tr.find('.service-amount').val().replace(',', ''),
                                });
                            }
                        });

                        // Save additional services.
                        $.ajax({
                            url: $app_url + '/csr/job-order/jo-services-additional-save',
                            type: 'post',
                            data: {
                                'joborder_id'               : $joborder_id,
                                'services_price_total'      : $('#services-grand-total').data('amt') ? $('#services-grand-total').data('amt') : 0,
                                'services_payment_method'   : $('#services_payment_method').val(),
                                'services_terms'            : Number($('#services_terms').val()),
                                'services_details'          : $services,
                            },
                            success: function(data) {
                                $.confirm({
                                    draggable: true,
                                    title: false,
                                    type: 'blue',
                                    content: '<p class="text-center"><i class="fa fa-fw fa-check text-bold text-primary" style="font-size:54px"></i></p>' +
                                            '<p class="text-center">Additional services was successfully added, please proceed to teller for the payment.</p>',
                                    buttons: {
                                        yes: {
                                            text: '<small>[ENTER]</small> got it!',
                                            btnClass: 'btn-primary',
                                            keys: ['enter']
                                        }
                                    }
                                });

                                $jc.close();
                                $dt.search($('#table-joborders_filter input').val()).draw();
                            },
                            error: function(result) {
                                $.each(result.responseJSON, function(field, message) {
                                    $('#'+field).parent().addClass('has-error').prop('title', message);
                                });

                                $jc.hideLoading(true);
                            }
                        });

                        return false;
                    }
                }
            }
        });
    });
</script>
@endsection