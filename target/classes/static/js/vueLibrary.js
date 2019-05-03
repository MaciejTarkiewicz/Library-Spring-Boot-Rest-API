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
            login: '',
            l: '',
            info: null,
            edit: false,
            borrow: false,
            status: false,
        },
        methods: {
            addBook() {
                document.location.replace("/library/add");

            },
            logout() {
                axios.get('/api/logout');
                document.location.replace("/");
            },
            deleteBook(id) {
                console.log(id);
                axios.delete("/api/library/" + id).then(function (response) {
                    document.location.replace("/library/all");
                }).catch(err => {
                    alert("You can't delete this book!")
                });
            },
            editBook(id) {
                axios
                    .get('/api/library/edit?id=' + id)
                    .then(response => {
                        this.info = response.data;
                        console.log(this.info.author);
                    })
                    .catch(error => {
                    })
                    .finally(() => this.edit = true)
            },
            saveBook(id) {
                axios({
                    method: 'put',
                    url: '/api/library/edit?id=' + id,
                    data: {
                        title: this.info.title,
                        author: this.info.author,
                        year: this.info.productionYear,
                        type: this.info.type
                    }
                }).then(function (response) {
                    document.location.replace("/library/all");

                }).catch(err => {
                    alert("Invalid!")
                });
            },
            cancel() {
                document.location.replace("/library/all");

            },
            justTesting(user_id) {
                return user_id !== null;

            },

            myLibrary(username) {
                document.location.replace("/library/user?username=" + username);

            },

            BorrowBook(id) {
                try {
                    axios.put("/api/library/borrow/" + id).then(function (response) {
                        document.location.replace("/library/all");
                    }).catch(err => {

                    }).finally(() => this.borrow = true)
                }catch(error){

                }finally {
                    this.status = true
                }

            },

        },
        async created() {
            const {data} = await axios.get('/api/library/all');
            this.username = await axios.get('/api/library/user');
            this.l = data;
            console.log(this.status);

        }


    })

};