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
            GiveBook(id){
                console.log(id);
                axios.put("/api/library/user/" + id).then(function (response) {
                    document.location.replace("/library/all");
                }).catch(err => {
                    alert("You can't delete this book!")
                });
            },
            Library(){
                document.location.replace("/library/all");
            },
            logout() {
                axios.get('/api/logout');
                document.location.replace("/");
            },

        },
        async created() {
            //const { data } = await axios.get('/api/library');
            const { data } = await axios.get('/api/library');
            this.username = await axios.get('/api/library/user');
            this.l = data;
            console.log(this.l);

        }


    })

};