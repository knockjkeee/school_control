<#macro detail name det img>

<#--<#list uuid as list>-->
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" data-toggle="modal" id="bnt_details">
        Детали
    </button>

    <!-- Modal -->
    <div class="modal fade" tabindex="-1" role="dialog" id="model-details"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">

    <script src="static/create_dynamic_id.js"></script>
<#--<script src="http://localhost:8008/static/create_dynamic_id.js"></script>-->

    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">${name}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="container-fluid p-4">
                <div class="card-group">
                    <div class="card">
                        <img class="card-img-top" src="${det}" alt="Card image cap">
                        <div class="card-body">
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">Детекция</small>
                        </div>
                    </div>
                    <div>&nbsp;&nbsp;&nbsp;</div>
                    <div class="card">
                        <img class="card-img-top" src="${img}" alt="Card image cap">
                        <div class="card-body">
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">Верификация</small>
                        </div>
                    </div>
                </div>


                <#--<div class="modal-body">-->
                <#--${id}-->
                <#--</div>-->
                <#--<div class="modal-footer">-->
                <#--<button type="button" class="btn btn-secondary" data-dismiss="modal">Close-->
                <#--</button>-->
                <#--<button type="button" class="btn btn-primary">Save changes</button>-->
                <#--</div>-->
            </div>
        </div>
    </div>


<#--</#list>-->

</#macro>