

<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Books </span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="bookCtrl.successMessage">{{bookCtrl.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="bookCtrl.errorMessage">{{bookCtrl.errorMessage}}</div>
                <form ng-submit="bookCtrl.submit()" name="myForm" class="form-horizontal">
                    <input type="hidden" ng-model="bookCtrl.book.id" />
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="btitle">Title</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="bookCtrl.book.title" id="btitle" class="username form-control input-sm" placeholder="Enter title"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="author">Author</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="bookCtrl.book.author" id="author" class="form-control input-sm" placeholder="Enter author"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit"  value="{{!bookCtrl.book.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                            <button type="button" ng-click="bookCtrl.reset()" class="btn btn-warning btn-sm" >Reset Form</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Books</span></div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>TITLE</th>
                        <th>AUTHOR</th>
                        <th width="100"></th>
                        <th width="100"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="u in bookCtrl.getAllBooks()">
                        <td>{{u.title}}</td>
                        <td>{{u.author}}</td>
                        <td><button type="button" ng-click="bookCtrl.editBook(u.id)" class="btn btn-success custom-width">Edit</button></td>
                        <td><button type="button" ng-click="bookCtrl.removeBook(u.id)" class="btn btn-danger custom-width">Remove</button></td>
                        <td><button type="button" ng-click="bookCtrl.removeBook(u.id)" class="btn btn-success custom-width">Borrow</button></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>