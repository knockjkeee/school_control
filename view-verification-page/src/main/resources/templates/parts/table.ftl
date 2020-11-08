<#import "details.ftl" as d>
<#import "pager.ftl" as p>
<#macro table>


    <div class="table-responsive m-auto text-center align-bottom">
        <#--<table class="table table-hover m-auto table-striped table-bordered dt-responsive nowrap" style="width:100% id="example">-->
        <table class="table table-sm table-hover m-auto display nowrap" style="width:100%" id="example">
            <thead>
            <tr>
                <th scope="col" >#</th>
                <th scope="col">№ Турникета</th>
                <th scope="col">ID СКУД</th>
                <th scope="col">ФИО</th>
                <th scope="col">Дата прохода</th>
                <th scope="col">Время прохода</th>
                <th scope="col">ID ISS</th>
                <th scope="col">ФИО ISS</th>
                <th scope="col">Дата верификации</th>
                <th scope="col">Время верификации</th>
                <th scope="col">Сравнивание</th>
                <th scope="col">Детали</th>

            </tr>
            </thead>
            <tbody id="table">
            <#list page.content as item>
                <#if item.result = '112'>
                    <tr class="alert alert-warning">
                        <th scope="row">${item?index + 1}</th>
                        <td>${item.turnstileAddr}</td>
                        <td>${item.idTransit}</td>
                        <td class="text-left">${item.usernameTransit}</td>
                        <td>${item.dataTransit}</td>
                        <td>${item.timeTransit}</td>
                        <td>${item.idVerification}</td>
                        <td>${item.usernameVerification}</td>
                        <td>${(item.dataVerification)!""}</td>
                        <td>${(item.timeVerification)!""}</td>
                        <td>${item.result}</td>
                        <td>
                            <@d.detail name="${item.usernameTransit}" det="${item.detectionFaceImageUrl}" img="${item.personFaceImageUrl}"></@d.detail>
                        </td>
                    </tr>
                <#elseif item.result = 'Инцидент' || item.usernameVerification = 'Инцидент'>
                    <tr class="alert alert-danger">
                        <th scope="row">${item?index + 1}</th>
                        <td>${item.turnstileAddr}</td>
                        <td>${item.idTransit}</td>
                        <td class="text-left">${item.usernameTransit}</td>
                        <td>${item.dataTransit}</td>
                        <td>${item.timeTransit}</td>
                        <td>${item.idVerification}</td>
                        <td>${item.usernameVerification}</td>
                        <td>${(item.dataVerification)!""}</td>
                        <td>${(item.timeVerification)!""}</td>
                        <td>${item.result}</td>
                        <td>
                            <@d.detail name="${item.usernameTransit}" det="${item.detectionFaceImageUrl}" img="${item.personFaceImageUrl}"></@d.detail>
                        </td>
                    </tr>
                <#elseif item.result = 'Ok'>
                    <tr class="alert alert-success">
                        <th scope="row">${item?index + 1}</th>
                        <td>${item.turnstileAddr}</td>
                        <td>${item.idTransit}</td>
                        <td class="text-left">${item.usernameTransit}</td>
                        <td>${item.dataTransit}</td>
                        <td>${item.timeTransit}</td>
                        <td>${item.idVerification}</td>
                        <td>${item.usernameVerification}</td>
                        <td>${(item.dataVerification)!""}</td>
                        <td>${(item.timeVerification)!""}</td>
                        <td>${item.result}</td>
                        <td>
                            <@d.detail name="${item.usernameTransit}" det="${item.detectionFaceImageUrl}" img="${item.personFaceImageUrl}"></@d.detail>
                        </td>
                    </tr>

                <#else>
                    <tr>
                        <th scope="row">${item?index + 1}</th>
                        <td>${item.turnstileAddr}</td>
                        <td>${item.idTransit}</td>
                        <td class="text-left">${item.usernameTransit}</td>
                        <td>${item.dataTransit}</td>
                        <td>${item.timeTransit}</td>
                        <td>${item.idVerification}</td>
                        <td>${item.usernameVerification}</td>
                        <td>${(item.dataVerification)!""}</td>
                        <td>${(item.timeVerification)!""}</td>
                        <td>${item.result}</td>
                        <td>
                            <@d.detail name="${item.usernameTransit}" det="${item.detectionFaceImageUrl}" img="${item.personFaceImageUrl}"></@d.detail>
                        </td>
                    </tr>
                </#if>


            </#list>
            </tbody>
        </table>
    </div>
    <div class="p-2"></div>
    <@p.pager url page />
</#macro>