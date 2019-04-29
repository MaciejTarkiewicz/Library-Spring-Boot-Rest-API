window.onload = () => {
    new Vue({
        el: "#login_vue",
        data: {
            username: '',
            password: '',
        },
        methods: {
            signUp() {
                axios({
                    method: 'post',
                    url: '/api/login',
                    data: {username: this.username, password: this.password}
                }).then(function (response) {
                    document.location.replace("/library");
                }).catch(err => {
                    alert("Invalid username or password!")
                });
            },
            home(){
                document.location.replace("/");
            }
        },
    })

};
