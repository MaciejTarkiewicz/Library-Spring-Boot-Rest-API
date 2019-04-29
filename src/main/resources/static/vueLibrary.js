window.onload = () => {
    new Vue({
        el: "#library_vue",
        data: {
            id: '',
            title: '',
            author: '',
            year: '',
            type: '',
            username: '',
            login:'',
            l: '',
            info: null,
            edit: false
        },
        methods: {
            addBook() {
                document.location.replace("/library/add");

            },
            logout() {
                axios.get('/api/logout');
                document.location.replace("/");
            },
            deleteBook(id){
                console.log(id);
                axios.delete("/api/library/" + id).then(function (response) {
                    document.location.replace("/library");
                }).catch(err => {
                    alert("Invalid username or password!")
                });
            },
            editBook(id)  {
                axios
                    .get('/api/library/edit?id=' + id)
                    .then(response => {
                        this.info = response.data;
                        console.log(this.info.author);
                    })
                    .catch(error => {
                        console.log(error)
                        // this.errored = true
                    })
                    .finally(() => this.edit = true)
            },
            saveBook(id) {
                axios({
                    method: 'put',
                    url: '/api/library/edit?id=' + id,
                    data: {title: this.info.title, author: this.info.author, year: this.info.productionYear, type: this.info.type}
                }).then(function (response) {
                    document.location.replace("/library");
                }).catch(err => {
                    console.log(this.info.title);
                    console.log(err);
                    alert("Invalid!")
                });
            },
            cancel() {
                document.location.replace("/library");

            },

        },
        async created() {
            const { data } = await axios.get('/api/library');
            this.username = await axios.get('/api/library/user');
            this.l = data;

        }


    })

};