window.onload = () => {
    new Vue({
        el: "#app",
        data: {
            username: '',
            password: '',
            confirmPassword: '',
            email: '',
            user: ''
        },
        methods: {
            register() {
               if(this.password !== this.confirmPassword){
                    alert("Passwords do not match");
                    return;
                }
                axios({
                    method: 'post',
                    url: 'register',
                    data: {username: this.username, password: this.password, confirmPassword: this.confirmPassword, email: this.email}
                }).then(function (response) {
                    //this.user = username.toString() + "/library";
                    document.location.replace("/login");
                }).catch(err => {
                    if (err.response.status === 409) {
                        alert("Bad email format!")
                    }else{
                        alert("Fill in all fields!")
                    }
                });
            },
            logi(){
                document.location.replace("/login");
            }
        },
    })

}
