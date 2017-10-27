<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Users </span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
                <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="email">E-Mail</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.user.email" id="email" class="username form-control input-sm" placeholder="Enter e-mail"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="name">Name</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.user.name" id="name" class="form-control input-sm" placeholder="Enter name"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="role">Librarian</label>
                            <div class="col-md-7">
                                <input type="checkbox"
                                       ng-model="ctrl.user.role"
                                       ng-true-value="'ROLE_LIBRARIAN'"
                                       ng-false-value="'ROLE_USER'"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="suspended">Suspended</label>
                            <div class="col-md-7">
                                <input type="checkbox" ng-model="ctrl.user.suspended" />
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit"  value="Save" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                            <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" >Reset Form</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of users</span></div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>E-Mail</th>
                        <th>Name</th>
                        <th>Role</th>
                        <th>Suspended</th>
                        <th width="100"></th>
                        <th width="100"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="u in ctrl.getAllUsers()">
                        <td>{{u.email}}</td>
                        <td>{{u.name}}</td>
                        <td>{{u.role}}</td>
                        <td>{{u.suspended}}</td>
                        <td><button type="button" ng-click="ctrl.editUser(u.email)" class="btn btn-success custom-width">Edit</button></td>
                        <td><button type="button" ng-click="ctrl.removeUser(u.email)" class="btn btn-danger custom-width">Remove</button></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>