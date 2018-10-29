// Application module
var crudApp = angular.module('crudApp',[]);
crudApp.controller("DbController",['$scope','$http', function($scope,$http){
$scope.show_form = true;

const urlService = 'http://localhost:8080/api/v1/rest' 
// Function to get employee details from the database
$('#pessForm').css('display', 'none');
$('#empForm').css('display', 'none');

mascaraTelefone();
getInfo();

function mascaraTelefone(){
    $("input.telefone")
    .mask("(99) 9999-99999")
    .focusout(function (event) {  
        var target, phone, element;  
        target = (event.currentTarget) ? event.currentTarget : event.srcElement;  
        phone = target.value.replace(/\D/g, '');
        element = $(target);  
        element.unmask();  
        if(phone.length > 10) {  
            element.mask("(99) 99999-9999");  
        } else {  
            element.mask("(99) 9999-99999");  
        }  
    });
}

function getInfo(){
    
    // Sending request to EmpDetails.php files
    $http.get(`${urlService}/pessoas`).then(function(reponse){
    // Stored the returned data into scope
    console.log("data", reponse.data);
    $scope.details = reponse.data;
    });
}

$scope.insertInfo = function(info){
    console.log("info", info);
    $http.post(`${urlService}/pessoa/save`,
            {   "nome":info.nome,
                "cpf":info.cpf,
                "tipoLogradouro":info.tipoLogradouro,
                "logradouro":info.logradouro,
                "numero": info.numero,
                "bairro": info.bairro,
                "cidade": info.cidade,
                "estado": info.estado,
                "telefoneCelular": info.telefoneCelular,
                "id": $scope.pessoa ? $scope.pessoa.id : null

            })
            .then(function(response){
                debugger
                let {data} = response
                console.log("data Save", data);
                if (data) {
                getInfo();
                // Hide details insertion form
                $('#pessForm').css('display', 'none');
                }
            });
}

$scope.formToggle = function () {
    console.log("Togle");
    $('#pessForm').slideToggle();
    // $('#editForm').css('display', 'none');
    $('#empForm').css('display', 'none');
    $scope.pessoa = {};
    $('#formTitle').text("Inserir Pessoa")
    
}

$scope.pessoa = {};

$scope.editInfo = function(info){
    $scope.pessoa = info;
    console.log("Current user", $scope.pessoa);
    // $('#empForm').slideUp();
    // $('#editForm').slideToggle();
    $('#pessForm').slideToggle();
    $('#formTitle').text("Atualizar Pessoa")
}

$scope.UpdateInfo = function(info){
    $http.post(`${urlService}/pessoa/save/${info.emp_id}`,
        {"id":info.emp_id,"name":info.emp_name,"email":info.emp_email,"address":info.emp_address,"gender":info.emp_gender})
        .success(function(data){
            $scope.show_form = true;
            if (data == true) {
                getInfo();
            }
        });
}

    $scope.deleteInfo = function(info){

        if (window.confirm(`Você realmente remover ${info.nome} ?`)) { 
            $http.delete(`${urlService}/pessoa/remove/${info.id}`,{})
            .then(function(response){
                    if (response) {
                        getInfo();
                    }
                });
            }
        }
    }])