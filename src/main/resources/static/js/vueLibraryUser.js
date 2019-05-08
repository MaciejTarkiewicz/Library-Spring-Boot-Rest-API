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
            rating:false,
            rating_add:''
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

            rating12() {
                this.rating = true;
            },

            // Rating(id,username) {
            //     axios({
            //         method: 'post',
            //         url: '/api/library/user/rating/' + id,
            //         data: {rating: this.rating}
            //     }).then(function (response) {
            //         document.location.replace("/library/user?username" + username);
            //     }).catch(err => {
            //         if (err.response.status === 400) {
            //             this.error = true;
            //             this.info = 'Invalid rating!';
            //         }
            //     });

        },
        async created() {
            //const { data } = await axios.get('/api/library');
            const { data } = await axios.get('/api/library');
            this.username = await axios.get('/api/library/user');
            this.l = data;

        }


    })

};