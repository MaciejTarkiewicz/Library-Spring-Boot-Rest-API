window.onload = () => {
    new Vue({
        el: "#library",
        data: {
            id: '',
            title: '',
            author: '',
            productionYear: '',
            type: '',
            username: '',
            login:'',
            l: ''
        },
        methods: {
            AddBook() {
                document.location.replace("/library/add");

            },
            logout() {
                axios.get('/api/logout');
                document.location.replace("/");
            },
            Delete(id){
                console.log(id);
                axios.delete("/library/" + id).then(function (response) {
                    document.location.replace("/library");
                }).catch(err => {
                    alert("Invalid username or password!")
                });
            },

        },
        async created() {
            const { data } = await axios.get('/api/library');
            this.username = await axios.get('/api/library/user');git
            this.l = data;

        }


    })

};