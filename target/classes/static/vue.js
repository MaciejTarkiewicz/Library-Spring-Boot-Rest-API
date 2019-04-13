import axios from 'axios';
window.onload = () => {
    new Vue({
        el: "#app",
        data: {
            pages: [
                {text: "Username"},
                {text: "Password"},
                {text: "Confirm Password"},
                {text: "Email"},
                {text: "Confirm Email"}
            ],
            loginPage: [
                {text: "Username"},
                {text: "Password"},
            ]

        },
        methods:{
            someMethod(){
                axios.get('http://localhost:8080').then(res => {}).catch(err => {});
            }
        },
    })

}