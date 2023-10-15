    $currenttime = date('d.m.Y, H:i:s');

    $url = 'https://fcm.googleapis.com/fcm/send';

    

    # read through all registered devices

    $query = "select cid from table_with_stored_cids";

    $result = mysql_query($query);

    while ($row = mysql_fetch_object($result)) {

        $cid = $row->cid;

        $payload = '{

        "to": "' . $cid . '",

        "data": {

          "title": "New Job Order",

          "body": "' . $text . '\nSent at ' . $currenttime . '",

          "mutable_content": true,

          "sound": "Tri-tone",

          "click-action": "https://www.unspontan.com/index.php",

          }

        }

        ';


        // open connection

        $ch = curl_init();

        // set the url, number of POST vars, POST data

        curl_setopt($ch, CURLOPT_URL, $url);

        curl_setopt($ch, CURLOPT_HTTPHEADER, array(

        'Content-Type: application/json',

        'Authorization: key=yourserversidekey'

        ));  # key is server key from firebase console 

        curl_setopt( $ch, CURLOPT_POSTFIELDS, $payload );

        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true); # This will make curl_exec return the data instead of outputting it.

        // execute post

        $result = curl_exec($ch);

        // close connection

        curl_close($ch);


   } # while