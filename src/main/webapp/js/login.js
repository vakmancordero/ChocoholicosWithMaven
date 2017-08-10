$(document).ready(function() {
    $('.ui.form').form({
        fields: {
            email: {
                identifier  : 'email',
                rules: [
                    {
                        type   : 'empty',
                        prompt : 'Por favor, introduce tu e-mail'
                    },
                    {
                        type   : 'email',
                        prompt : 'Por favor, introduce un e-mail válido'
                    }
                ]
            },
            password: {
                identifier  : 'password',
                rules: [
                    {
                        type   : 'empty',
                        prompt : 'Por favor, introduce tu contraseña'
                    },
                    {
                        type   : 'length[6]',
                        prompt : 'Tu contraseña debe tener al menos 6 caracteres'
                    }
                ]
            }
        }
    });
});