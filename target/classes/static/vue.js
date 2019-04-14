import axios from 'axios';

window.onload = () => {
    new Vue({
        el: "#app",
        mounted() {
            Event.$on('logged-in', function () {
                window.location.replace("/");
            })
        },
        data: {
            username: '',
            password: '',
            confirm_password: '',
            email: ''
        },
        methods: {
            register() {
                axios({
                    method: 'post',
                    url: 'register',
                    data: {username: this.username, password: this.password, confirm_password: this.confirm_password, email: this.email}
                }).then(function (response) {
                    document.location.replace("/");
                });
            }
        },
    })



}