<#macro common>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Главная страница</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <body>
    <div class="p-2"></div>

    <div class="container-fluid p-12">
        <div class="header-h1-center text-center alert alert-secondary p-2">
            <h1>Главная страница</h1>
            <#--<p>Resize this responsive page to see the effect!</p>-->
        </div>
        <#nested>
    </div>
    <#--<script src="/static/search.js"></script>-->

    </script>
    </body>
    </html>
</#macro>