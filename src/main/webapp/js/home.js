/* global swal */

var app = angular.module('chocoApp', []);

app.controller('ChocoController', function ($scope, $http) {
    
    $scope.user = {};
    $scope.consultation = {};
    $scope.provider = {};
    $scope.member = {};
    $scope.service = {};
    
    $scope.finder = {};
    
    $scope.members = [];
    $scope.providers = [];
    $scope.consultations = [];
    
    // Simple Arrays
    $scope.cities = [];
    $scope.states = [];
    
    $scope.counterUsers;
    $scope.counterConsultations;
    
    angular.element(document).ready(function () {
        
        initElements();
        initValidator();
        
        fillSimpleArrays();
        
        fillConsultations();
        fillMembers();
        fillProviders();
        
    });
    
    $scope.initCity = function () {
        $scope.user.city = 'Tuxtla Gutierrez';
    };
    
    $scope.initState = function () {
        $scope.user.state = 'Activo';
    };
    
    $scope.changeCity = function () {
        console.log($scope.user.city);
    };
    
    $scope.changeState = function () {
        console.log($scope.user.city);
    };
    
    function fillSimpleArrays() {
        
        $scope.cities.push('Tuxtla Gutierrez');
        $scope.cities.push('San Cristóbal');
        $scope.cities.push('Comitán');
        
        $scope.states.push('Activo');
        $scope.states.push('Inactivo');
        
    }
    
    function fillProviders() {
        
        $.ajax({
            type: 'POST',
            url: 'UserController',
            data: {
                action: "list",
                type: "provider"
            },
            success: function (providers) {
                $scope.providers = providers;
            }
        });
        
    }
    
    function fillMembers() {
        
        $.ajax({
            type: 'POST',
            url: 'UserController',
            data: {
                action: "list",
                type: "member"
            },
            success: function (members) {
                $scope.members = members;
            }

        });
        
    }
    
    function fillConsultations() {
        
        $.ajax({
            type: 'GET',
            url: 'ConsultationController',
            success: function (consultations) {
                console.log(consultations);
                $scope.consultations = consultations;
            }
        });
        
    }

    $(function initSearchProvider() {
        
        $('#search_provider').search({
            apiSettings: {
                url: 'UserController?word={query}&type=provider'
            },
            fields: {
                results : 'providers',
                title   : 'name',
                url     : 'html_url'
            },
            minCharacters : 1,
            onSelect: function(provider, response) {
                $scope.provider = provider;
            }
        });
        
    });
    
    $(function initSearchMember() {
        
        $('#search_member, #search_member_find').search({
            apiSettings: {
                url: 'UserController?word={query}&type=member'
            },
            fields: {
                results : 'members',
                title   : 'name',
                url     : 'html_url'
            },
            minCharacters : 1,
            onSelect: function(member, response) {
            
                var id = $(this).attr("id");
                
                if (id === "search_member_find") {
                    
                    console.log($scope.finder);
                    
                    $scope.finder.name = member.name;
                    $scope.finder.address = member.address;
                    $scope.finder.cp = member.cp;
                    $scope.finder.city = member.city;
                    $scope.finder.state = member.state;
                    
                    $scope.$apply();
                    
                    swal(
                        'Correcto!',
                        'Usuario encontrado!',
                        'success'
                    );
                    
                } else if (id === "search_member") {
                    
                    $scope.member = member;
                    
                }                

            }
        });
        
    });
    
    $(function initSearchService() {
        
        $('#search_service').search({
            apiSettings: {
                url: 'UserController?word={query}&type=service'
            },
            fields: {
                results : 'services',
                title   : 'name',
                url     : 'html_url'
            },
            minCharacters : 1,
            onSelect: function(service, response) {
                $scope.service = service;
            }
        });
        
    });
    
    $scope.saveUser = function() {
        
        var userType = $scope.user.type;
        
        if (userType === "provider")
            if ($scope.user.password !== $scope.user.rePassword){
                swal(
                    'Error!',
                    'Las contraseñas no coinciden!',
                    'error'
                );
                return;
            }
        
        $.ajax({
            type: 'POST',
            url: 'UserController',
            data: {
                action : "save",
                user : JSON.stringify($scope.user)
            },
            success: function (result) {
                
                if (result === true) {
                    
                    if (userType === "provider")  $scope.providers.push($scope.user);
                    else $scope.members.push($scope.user);
                    
                    $scope.user = {};
                    
                    $scope.$apply();
                    
                    swal(
                        'Correcto!',
                        'Usuario registrado!',
                        'success'
                    );
            
                    $scope.openListUsersMl();
                    
                } else {
                    
                    console.log(result);
                    
                    swal(
                        'Error!',
                        'No se ha podido insertar al usuario! ' +
                        'El usuario ya existe',
                        'error'
                    );
                    
                }
                
            }

        });
        
    };
    
    $scope.editMember = function() {
        
        $.ajax({
            type: 'POST',
            url: 'UserController',
            data: {
                action : "edit_member",
                user : JSON.stringify($scope.user)
            },
            success: function (result) {
                
                console.log(result);
                
                if (result === true) {
                    
                    $scope.user = {};
                    
                    $scope.$apply();
                    
                    swal(
                        'Correcto!',
                        'Usuario editado!',
                        'success'
                    );
            
                    fillMembers();
                    
                    $scope.openListUsersMl();
                    
                } else {
                    
                    console.log(result);
                    
                    swal(
                        'Error!',
                        'No se ha podido editar al usuario!',
                        'error'
                    );
                    
                }
                
            }

        });
        
    };
    
    $scope.deleteMember = function (index) {
        
        var member = $scope.members[index];
        var id = member.id;
        
        swal({
            title: 'Estás seguro?',
            text: "Eliminarás al miembro: " + member.name,
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si, eliminar' 
        }).then(function () {
            
            $.ajax({
                type: 'POST',
                url: 'UserController',
                data: {
                    action : "delete_member",
                    id: id
                },
                success: function (result) {
                    
                    if (result === true) {
                        
                        $scope.members.splice(index, 1);

                        $scope.$apply();
                        
                        swal(
                            'Correcto!',
                            'Miembro eliminado!',
                            'success'
                        );

                    } else {

                        swal(
                            'Error!',
                            'No se ha podido eliminar al miembro!',
                            'error'
                        );

                    }

                }

            });
            
        });
        
    };
    
    $scope.deleteProvider = function (index) {
        
        var provider = $scope.providers[index];
        var id = provider.id;
        
        swal({
            title: 'Estás seguro?',
            text: "Eliminarás al proveedor: " + provider.name,
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si, eliminar' 
        }).then(function () {
            
            $.ajax({
                type: 'POST',
                url: 'UserController',
                data: {
                    action : "delete_provider",
                    id: id
                },
                success: function (result) {
                    
                    if (result === true) {
                        
                        $scope.providers.splice(index, 1);

                        $scope.$apply();
                        
                        swal(
                            'Correcto!',
                            'Proveedor eliminado!',
                            'success'
                        );

                    } else {

                        swal(
                            'Error!',
                            'No se ha podido eliminar al proveedor!',
                            'error'
                        );

                    }

                }

            });
            
        });
        
    };
    
    $scope.editProvider = function() {
        
        $.ajax({
            type: 'POST',
            url: 'UserController',
            data: {
                action : "edit_provider",
                user : JSON.stringify($scope.user)
            },
            success: function (result) {
                
                console.log(result);
                
                if (result === true) {
                    
                    $scope.user = {};
                    
                    $scope.$apply();
                    
                    swal(
                        'Correcto!',
                        'Usuario editado!',
                        'success'
                    );
            
                    fillProviders();
                    
                    $scope.openListUsersMl();
                    
                } else {
                    
                    console.log(result);
                    
                    swal(
                        'Error!',
                        'No se ha podido editar al usuario!',
                        'error'
                    );
                    
                }
                
            }

        });
        
    };
    
    $scope.saveConsultation = function() {
        
        var consultation = {};
        
        angular.copy($scope.consultation, consultation);
        
        consultation.provider = $scope.provider.id;
        consultation.member = $scope.member.id;
        consultation.service = $scope.service.id;
        
        $.ajax({
            type: 'POST',
            url: 'ConsultationController',
            data: {
                action : "save",
                consultation : JSON.stringify(consultation)
            },
            success: function (result) {
                
                if (result !== "false") {
                    
                    fillConsultations();
                    
                    $scope.consultation = {};
                    
                    $scope.closeAddConsultationMl();
                    
                    $scope.$apply();
                    
                    swal(
                        'Correcto!',
                        'Consulta creada!',
                        'success'
                    );
                    
                } else {
                    
                    swal(
                        'Error!',
                        'No se ha podido crear la consulta!',
                        'error'
                    );
                    
                }
                
            }

        });
        
    };
    
    $scope.generateReport = function (index) {
        
        var provider = $scope.providers[index];
        
        window.open("ReportController?id=" + provider.id, '_blank');
        
//        $.ajax({
//            type: 'POST',
//            url: 'ConsultationController',
//            data: {
//                action: "generate_report",
//                id: provider.id
//            },
//            success: function (result) {
//                
//                console.log(result);
//                
//            }
//            
//        });
            
    };
    
    // Abrir modal de añadir usuarios
    $scope.openAddUserMl = function() {
        
        var modal = $('#addUserMl');
        
        modal.modal({
            observeChanges: true,
            onHide: function(){
                $scope.user = {};
                
                console.log("Cerrando modal de búsqueda");
            },
            onShow: function() {
            }
        }).modal('show');
        
    };
    
    $scope.openListUserMlReport = function() {
        
        var modal = $('#listUsersMl');
        
        modal.modal({
            observeChanges: true,
            onHide: function(){
                $scope.user = {};
                
                console.log("Cerrando modal de búsqueda");
            },
            onShow: function() {
                
                swal({
                    title: '<i>Generar reportes</i>',
                    type: 'info',
                    html:
                            'Elige un proveedor de la lista y da click en <b>Generar reporte</b>',
                    showCloseButton: true,
                    showCancelButton: true,
                    confirmButtonText:
                            'Ok!',
                    cancelButtonText:
                            'Cancelar'
                }).then(function () {
                    
                }, function (dismiss) {
                    if (dismiss === 'cancel') {
                        modal.modal('hide');
                    }
                });
                
            }
        }).modal('show');
        
    };
    
    $scope.openEditMemberMl = function(index) {
        
        $scope.user = $scope.members[index];
        
        console.log($scope.user);
        
        $('#editProviderMl').modal({
            observeChanges: true,
            onHide: function(){
                $scope.user = {};
                console.log("Cerrando modal editar miembro");
            },
            onShow: function() {
                
            }
        }).modal('show');
        
    };
    
    $scope.closeEditMemberMl = function() {
        $('#editProviderMl').modal('hide');
    };
    
    $scope.openEditProviderMl = function(index) {
        
        $scope.user = $scope.providers[index];
        
        console.log("here");
        
        console.log($scope.user);
        
        $('#editProviderMl').modal({
            observeChanges: true,
            onHide: function(){
                $scope.user = {};
            },
            onShow: function() {
                console.log("Cerrando modal editar miembro");
            }
        }).modal('show');
        
    };
    
    $scope.closeEditProviderMl = function() {
        $('#editProviderMl').modal('hide');
    };
    
    // Abrir modal de añadir consultas
    $scope.openAddConsultationMl = function() {
        
        $('#addConsultationMl').modal({
            observeChanges: true,
            onHide: function(){
                $scope.consultation = {};
            },
            onShow: function() {
                console.log("Cerrando modal de añadir consultas");
            }
        }).modal('show');
        
    };
    
    // Cerrar modal de añadir consultas
    $scope.closeAddConsultationMl = function() {
        $('#addConsultationMl').modal('hide');
    };
    
    // Abrir modal de listar usuarios [Miembros y Proveedores]
    $scope.openListUsersMl = function() {
        $('#listUsersMl').modal({observeChanges: true}).modal('show');
    };
    
    // Abrir modal de listar consultas
    $scope.openListConsultationsMl = function() {
        $('#listConsultationsMl').modal({observeChanges: true}).modal('show');
    };
    
    $scope.openFindMemberMl = function() {
        
        $('#findMemberMl').modal({
            onHide: function(){
                $scope.finder = {};
            },
            onShow: function(){
                console.log('shown');
            }
        }).modal('show');
        
    };
    
    function initElements() {
        
        $('.masthead').visibility({
            once: false,
            onBottomPassed: function() {
                $('.fixed.menu').transition('fade in');
            },
            onBottomPassedReverse: function() {
                $('.fixed.menu').transition('fade out');
            }
        });
        
        $('.ui.sidebar').sidebar('attach events', '.toc.item');
        
        $('.ui.dropdown').dropdown();
        
        $('.special.cards .image').dimmer({on: 'hover'});
        
        $('#myContainer').transition('pulse');
        
    }
    
    function initValidator() {
        
        $('#addUserForm').form({
            fields: {
                type: {
                    identifier  : 'type',
                    rules: [
                        {
                            type   : 'empty',
                            prompt : 'Por favor, especifica un tipo'
                        }
                    ]
                }
            }
        });
        
    }
    
});