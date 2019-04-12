<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="main.css" media="screen"/>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="vue.js"></script>
</head>


<body>
<div id="app">
    {{ test }}
    <h2>Registration:</h2>
    <ol>
        <li v-for="page in pages">
            <label>
                <input type="text"  name = page.text placeholder="edit me"
                       v-model="message" >
                <span>
          {{ page.text }}
        </span>

            </label>
        </li>
        <button type="submit" value="Submit">
            Register
        </button>
    </ol>
    <h2>
    </h2>
    <h2>Sign up:</h2>
    <ol>
        <li v-for="item in loginPage">
            <label>
                <input type="text"  name = item.text placeholder="edit me"
                       v-model="message" >
                <span>
          {{item.text}}
        </span>

            </label>
        </li>
    </ol>

    <button type="submit" value="Submit">
        Sign
    </button>
</div>
</body>
</html>



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
    })

}


body {
    background: #20262E;
    padding: 20px;
    font-family: Helvetica;
}

#app {
    background: #fff;
    border-radius: 4px;
    padding: 20px;
    transition: all 0.2s;
}

li {
    margin: 8px 0;
}

h2 {
    font-weight: bold;
    margin-bottom: 15px;
}

del {
    color: rgba(0, 0, 0, 0.3);
}
