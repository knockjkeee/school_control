<#import "parts/common.ftl" as c>
<#import "parts/load_data.ftl" as d>
<#import "parts/table.ftl" as t>
<#import "parts/pager.ftl" as p>
<#import "parts/nav.ftl" as n>

<@c.common>

    <@n.nav />

    <div class="p-2"></div>
    <div class="header-h1-center text-center alert alert-secondary p-2">
        <h1>Контроль входа</h1>
        <#--<p>Resize this responsive page to see the effect!</p>-->
    </div>
    <@d.loadData />
    <#--<form method="post" action="/">-->
        <#--&lt;#&ndash;<input type="file" hidden value="${page.content}"/>&ndash;&gt;-->
        <#--<button type="submit"><img src="https://img.icons8.com/office/80/000000/export-csv.png"></button>-->
    <#--</form>-->

    <#--<a href="/download/data.csv"><img src="https://img.icons8.com/office/80/000000/export-csv.png" alt="logo"></a>-->


    <@p.pager url page />

    <div class="form-group">
        <div class="input-group input-group-md">
            <div class="input-group-prepend">
                <span class="input-group-text" id="inputGroup-sizing-lg">Поиск на странице</span>
            </div>
            <input type="text" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm"
                   id="search_table" placeholder="Search..">
        </div>
    </div>

    <div class="m-3"></div>

    <@t.table />
<#--<script>-->
<#--$("#button_dialog").click().hide();-->
<#--</script>-->

<#--<script>-->
<#--$('.accordion-toggle').click(function(){-->
<#--$('.hiddenRow').hide();-->
<#--$(this).next('tr').find('.hiddenRow').show();-->
<#--});-->
<#--</script>-->
<#--<script src="/static/save.js"></script>-->

</@c.common>
