<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="save.do" method="post" enctype="multipart/form-data">
        <input type="number" name="id" value="1">
        <input type="text" name="name" value="Jean">
        <input type="file" name="badge" id="">
        <input type="submit" value="Valider">
    </form>
    <a href="test.do?nom=Test&&poids=42">Test</a>
    <p><a href="testJson.do">Json test</a></p>
    <p><a href="ha.do">Json test2</a></p>
    <p><a href="hey.do">Model test</a></p>
    <p><a href="login.do">Se Connecter</a></p>
    <p><a href="admin.do">Page admin</a></p>
    
</body>
</html>