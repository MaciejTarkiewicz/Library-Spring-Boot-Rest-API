import axios from 'axios';
window.onload = () => {
    new Vue({
        el: "#app",
        data: {
            message_username : '',

        },
        methods:{
            someMethod(){
                axios.post('http://localhost:8080',{data: { message_username}});
            }
        },
    })

}