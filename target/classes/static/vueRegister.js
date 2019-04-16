window.onload = () => {
    new Vue({
        el: "#app",
          // mounted() {
        //     Event.$on('logged-in', function () {
        //         window.location.replace("/");
        //     })
        // },
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
                });
            }
        },
    })



}
