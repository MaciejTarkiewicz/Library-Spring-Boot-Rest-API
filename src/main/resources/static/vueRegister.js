window.onload = () => {
    new Vue({
        el: "#app",
        data: {
            username: '',
            password: '',
            confirmPassword: '',
            email: ''
        },
        methods: {
            register() {
                axios({
                    method: 'post',
                    url: 'register',
                    data: {username: this.username, password: this.password, confirmPassword: this.confirmPassword, email: this.email}
                }).then(function (response) {
                    document.location.replace("/welcome");
                }).catch(err => {

                });
            }
        },
    })



}
