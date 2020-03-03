<#import "parts/common.ftl" as c>

<@c.common>


    <div class="container-fluid p-12 col-md-8">
        <div class="card-deck modal-dialog-centered">
            <#if status = 'true'>
                <div class="card" style="width: 10%;">
                    <a href="/api/verification/"><img class="card-img-top" src="static/img/Вход.jpg"
                                                      alt="Card image cap"></a>
                    <div class="card-footer text-center">
                        <small class="text">КОНТРОЛЬ ВХОДА</small>
                    </div>
                </div>

                <div class="card" style="width: 10%;">
                    <a href="/api/outcontrol/"><img class="card-img-top" src="static/img/Выход.jpg"
                                                   alt="Card image cap"></a>
                    <div class="card-footer text-center">
                        <small class="text">КОНТРОЛЬ ВЫХОДА</small>
                    </div>
                </div>

                <div class="card" style="width: 10%;">
                    <a href="http://172.18.136.46:8091/"><img class="card-img-top" src="static/img/112.jpg"
                                                    alt="Card image cap"></a>
                    <div class="card-footer text-center">
                        <small class="text">ПУЛЬТ ОХРАНЫ</small>
                    </div>
                </div>

            <#else>
                <div class="card text-white bg-danger mb-3" style="width: 10rem;" data-toggle="tooltip" data-placement="top"
                     title="Сервис не доступен">
                    <img class="card-img-top" src="static/img/Вход.jpg"
                                                      alt="Card image cap">
                    <div class="card-footer text-center">
                        <small class="text">КОНТРОЛЬ ВХОДА</small>
                    </div>
                </div>
                <div class="card text-white bg-danger mb-3" style="width: 10rem;" data-toggle="tooltip" data-placement="top"
                     title="Сервис не доступен">
                    <img class="card-img-top" src="static/img/Выход.jpg" alt="Card image cap">
                    <div class="card-footer text-center">
                        <small class="text">КОНТРОЛЬ ВЫХОДА</small>
                    </div>
                </div>
                <div class="card text-white bg-danger mb-3" style="width: 10rem;" data-toggle="tooltip" data-placement="top"
                     title="Сервис не доступен">
                    <img class="card-img-top" src="static/img/112.jpg" alt="Card image cap" data-toggle="tooltip"
                         data-placement="top" title="Сервис не доступен">
                    <div class="card-footer text-center">
                        <small class="text">ПУЛЬТ ОХРАНЫ</small>
                    </div>
                </div>
            </#if>

        </div>
    </div>

    <script>
        $(function () {
            $("[data-toggle='tooltip']").tooltip();
        });
    </script>

</@c.common>
