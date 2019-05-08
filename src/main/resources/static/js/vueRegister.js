window.onload = () => {
    new Vue({
        el: "#register_vue",
        data: {
            username: '',
            password: '',
            confirmPassword: '',
            email: '',
            user: '',
            info:'',
            error: false
        },
        methods: {
            register() {
                if(this.username.trim() === '' || this.password.trim()  === '' || this.confirmPassword.trim()  === '' || this.email.trim()  === ''){
                    this.error = true;
                    this.info = 'Error: Fill in all fields!';
                    return;
                }
               if(this.password !== this.confirmPassword){
                    this.error = true;
                    this.info = 'Passwords do not match';
                    return;
                }
                axios({
                    method: 'post',
                    url: '/api/register',
                    data: {username: this.username, password: this.password, confirmPassword: this.confirmPassword, email: this.email}
                }).then(function (response) {
                    document.location.replace("/login");
                }).catch(err => {
                    if (err.response.status === 409) {
                        this.error = true;
                        this.info = 'Error: Bad email format!';
                    }else if (err.response.status === 500) {
                        this.error = true;
                        this.info = 'Error: User with that username is already exist!';
                    }else{
                        this.error = true;
                        this.info = 'Error: Fill in all fields!';
                    }
                })
            },
            home(){
                document.location.replace("/");
            }
        },
    })

}
