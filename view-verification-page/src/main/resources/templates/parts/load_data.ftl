<#macro loadData>
    <#--<div class="m-5"></div>-->
    <!-- Button to Open the Modal -->
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" id="btn_loading">
        Open modal
    </button>

    <!-- The Modal -->
    <div class="modal fade" id="myModal">
        <div class="modal-dialog modal-dialog-centered p-4">
            <div class="modal-content col-md-8" id="load_data">
                <!-- Modal Header -->
                <div class="modal-header ">
                    <h4 class="modal-title">Loading data....</h4>
                    <div class="spinner-border text-primary"></div>
                    <button type="button" class="close" data-dismiss="modal" id="button_dialog">&bigotimes;</button>
                </div>
            </div>
        </div>
    </div>
    <script src="static/click.js"></script>
    <#--<script src="http://localhost:8008/static/click.js"></script>-->
</#macro>