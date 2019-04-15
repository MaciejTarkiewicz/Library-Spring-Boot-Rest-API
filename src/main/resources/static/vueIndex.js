window.onload = () => {
    new Vue({
        el: "#index",
        data: {
        },
        methods: {
            register() {
                axios.get(`http://localhost:8080/login`)
                    .then(response => {
                        // JSON responses are automatically parsed.
                        this.posts = response.data
                    })
                    .catch(e => {
                        this.errors.push(e)
                    });
            },
            sign_up() {
                axios.get(`http://localhost:8080/login`)
                    .then(response => {
                        // JSON responses are automatically parsed.
                        this.posts = response.data
                    })
                    .catch(e => {
                        this.errors.push(e)
                    });
            }
        },
    })



}
