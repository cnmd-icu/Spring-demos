package com.example.web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class index {

    @RequestMapping("/")
    public String Index() {
        System.out.println("这是Index页面");
        return "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }" +
                "div.index { width: 100%; height: 100%;}" +
                "header {  width: 100%; height: 10%; background-color: #4CAF50; color: white; padding: 10px 0; text-align: center; }"
                +
                "nav {  width: 100%; height: auto; margin: 20px; }" +
                "nav a { margin: 0 15px; text-decoration: none; color: #4CAF50; font-weight: bold; }" +
                "nav a:hover { text-decoration: underline; }" +
                ".content { width: 95%; height: 100%; margin: 0 auto; padding: 20px; background-color: white; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }"
                +
                "</style>" +
                "<script>" +
                "function loadContent(url) {" +
                "  fetch(url).then(response => response.text()).then(data => {" +
                "    document.getElementById('content').innerHTML = data;" +
                "  });" +
                "}" +
                "function doLogin(event) {" +
                "console.log('doLogin');" +
                "  event.preventDefault();" +
                " const username = document.getElementById('username').value;" +
                " const password = document.getElementById('password').value;" +
                "  fetch('/doLogin', {" +
                "    method: 'POST'," +
                "    headers: {" +
                "      'Content-Type': 'application/json'" +
                "    }," +
                "    body: JSON.stringify({ username, password })" +
                "  }).then(response => response.text()).then(data => {" +
                "    document.getElementById('content').innerHTML = data;" +
                "  }).catch(error => {" +
                "    console.error('Error:', error);" +
                "  });" +
                "}" +
                "function confirmLogout() {" +
                "  fetch('/doLogout', { method: 'POST' }).then(response => response.text()).then(data => {" +
                "    document.getElementById('content').innerHTML = data;" +
                "  }).catch(error => {" +
                "    console.error('Error:', error);" +
                "  });" +
                "}" +
                "</script>" +
                "</head>" +
                "<body>" +
                "<div class='index'>" +
                "<header><h1>这是默认首页</h1></header>" +
                "<nav>" +
                "<a href='#' onclick=\"loadContent('/hello')\">Hello</a>" +
                "<a href='#' onclick=\"loadContent('/test')\">Test</a>" +
                "<a href='#' onclick=\"loadContent('/login')\">Login</a>" +
                "<a href='#' onclick=\"loadContent('/logout')\">Logout</a>" +
                "</nav>" +
                "<main id='content' class='content' style='color:red'>这是当前页面的内容</main>" +
                "</div>" +
                "</body>" +
                "</html>";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "<div>Hello 页面内容</div>";
    }

    @RequestMapping("/test")
    public String test(HttpSession session) {
        if (session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn")) {
            return "<div>Test 页面内容 " +
                    "用户信息: " + session.getAttribute("username") +
                    "<a href='#' onclick=\"loadContent('/logout')\">退出登录</a>" +
                    "</div>";
        } else {
            return "<div>请先登录 <a href='#' onclick=\"loadContent('/login')\">登录</a></div>";
        }
    }

    @RequestMapping("/login")
    public String login() {
        return "<style>" +
                "form {padding: 20px; border-radius: 5px;}" +
                "input[type='text'], input[type='password'] { width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 5px; }"
                +
                "input[type='button'] { background-color: #4CAF50; color: white; padding: 10px; border: none; border-radius: 5px; cursor: pointer; }"
                +
                "input[type='button']:hover { background-color: #45a049; }" +
                "#loginMessage { color: red; margin-top: 10px; }" +
                "</style>" +
                "<form id='loginForm'>" +
                "<h2>登录</h2>" +
                "<label for='username'>用户名:</label>" +
                "<input type='text' id='username' name='username' placeholder='用户名：admin' required>" +
                "<label for='password'>密码:</label>" +
                "<input type='password' id='password' name='password' placeholder='密码：admin' required>" +
                "<input type='button' value='登录' onclick='doLogin(event)'>" +
                "<div id='loginMessage'></div>" +
                "</form>";
    }

    @PostMapping("/doLogin")
    public String doLogin(@RequestBody Map<String, String> body, HttpSession session) {
        if ("admin".equals(body.get("username")) && "admin".equals(body.get("password"))) {
            session.setAttribute("username", body.get("username"));
            session.setAttribute("loggedIn", true);
            return "<div>登录成功 <a href='#' onclick=\"loadContent('/test')\">进入Test</a></div>";
        } else {
            return "<div id='loginMessage'>用户名或密码错误</div>";
        }
    }

    @RequestMapping("/logout")
    public String logout() {
        return "<div>" +
                "<p>你确定要注销吗？</p>" +
                "<button onclick='confirmLogout()'>确认</button>" +
                "</div>";
    }

    @PostMapping("/doLogout")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "<div>已注销</div>";
    }
}