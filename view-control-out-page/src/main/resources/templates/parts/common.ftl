<#macro common>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <#--<meta http-equiv="refresh" content="3;url=/api/view" />-->
        <title>Контроль прохода</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <body>
    <div class="container-fluid p-6">
        <#nested>
    </div>
    <script src="static/search.js"></script>
    <#--<script src="http://localhost:8008/static/search.js"></script>-->
    <script>
        $("#button_dialog").click().hide();
    </script>
    </body>
    </html>
</#macro>