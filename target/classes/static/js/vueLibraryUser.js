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
            edit: false,
            borrow:false,
        },
        methods: {
            GiveBook(id,username) {
                try {
                    axios.put("/api/library/user/" + id).then(function (response) {
                        //document.location.replace("/library/all");
                        document.location.replace("/library/user?username" + username);
                        this.borrow = true;
                    }).catch(err => {
                        alert("You can't delete this book!")
                    });
                }catch(error){

                }finally {
                    this.borrow = true;
                }

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

        }


    })

};