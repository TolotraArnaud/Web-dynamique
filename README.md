# Web-dynamique : Création de Framework

# Prérequies

- Version Java: JDK-17
- Tomcat 10

# Utilisation

- Mettre le jar dans le lib de votre projet
- Il faut avoir quelque librairie comme `servlet-api.jar` et `gson-2.8.2.jar` dans votre Tomcat

- Veuiller configurer votre web.xml comme ceci :

  - Faite en sorte que tout les url se terminant par '.do' passe par la classe FrontServlet
  - Mettre le path "/WEB-INF/classes" dans la balise `param-value` du `param-name` de `pathClass` le chemin vers vos fichier sources

  - Exemple:

  ```xml
  <myxml>
    <servlet>
      <servlet-name>FrontServlet</servlet-name>
      <servlet-class>etu1869.framework.servlet.FrontServlet</servlet-class>
    <init-param>
      <param-name>pathClass</param-name>
      <param-value>/WEB-INF/classes</param-value>
      <description>Path to your classes</description>
    </servlet>
    <servlet-mapping>
      <servlet-name>FrontServlet</servlet-name>
      <url-pattern>*.do</url-pattern>
  </servlet-mapping>
  <myxml>
  ```

## Association url

- Pour associer une methode à un url :

  - Utiliser l'annotation `Url` et ajouter un attribut `value` qui aura comme valeur l'url que vous
    vouler associer à cette methode pour l'appeler
  - Noter que : La classe où se trouve la fonction a appelé doit etre annoté par l'annotation Choosen !!

  - Pour rediriger une méthode vers une `View` veuiller à cela fonction que vous appeler retourne une `ModelView`

    ```java
    @Scope
    public class Emp {

        String name;
        int age;

        @Url(value = "emp-page.do")
        public ModelView getEmpPage() {
        ///Your code here
        }
    }

    ```

## Transfert de données

- Pous envoyer des données depuis le backend vers le frontend avec `ModelView`:

  - La classe ModelView possède une fonction `addItem(String key, Object data)`qui permettra de transférer des données depuis les Models vers les Views
  - Pour indiquer le chemin de redirection vers la View, veuiller utiliser la fonction `setView(String view)`
    de la classe `ModelView` ou definir la vue au moment de l'instanciation `new ModelView(String view)`<br>
    Exemple :
    <br/>
    <br/>

  ```java
   @Url(value = "emp-page.do")
    public ModelView getPage() {
        ModelView view = new ModelView();
        Vector<String> params = new Vector<String>();
        params.add("Hello, my name is John Doe ");
        params.add("Lorem ipsum dolor sit amet consectetur adipisicing elit. Nostrum, iure eos.");
        params.add("Sapiente esse vero repudiandae explicabo!");
        params.add("Debitis sunt accusamus praesentium dolores numquam.");
        params.add("Nemo minus rem quam vitae quas, unde quaerat?");
        view.addItem("hellos", params);
        view.setView("Emp.jsp");
        return view;
    }
  ```

    <br>

- Pour envoyer des données depuis le frontend vers le backend:

  - Veuiller à ce que les noms des champs que vous vouler envoyer correspondate aux noms des attributs de la classe cible
  - Veuiller à ce que les attributs ont des getters et setters qui respectent la norme
  Exemple :
    <br/>
    <br/>

  ```java
   @Scope
    public class Emp {

        String name;
        int age;

        ...

        public void setName(String name){
            //your setters
        }
        public String getName(){
            //your getters
        }
        public void setAge(int age){
            //your setters
        }
        public String getAge(){
            //your getters
        }
    }
  ```

- Pour appeler une fonction qui prend en compte un ou des parametres :

  - Dans l'annotation `Url` ajouter un autre paramètre `param_name`

  <br/>

  ```java
    @Url(value = "add.do", param_name = "nom,poids")
    public Modelview (String nom, double poids) {
        Modelview mv = new Modelview("result.jsp");
        mv.addItem("nom", nom);
        mv.addItem("poids", poids);
        return mv;
    }
  ```

  - Veuillez à ce que les noms des champs envoyés correspondent aux nom des parametres de la fonction

   </br>
    -Exemple : <br>

  Votre classe :

  ```java
  @Url(value = "get-emp-by-id.do")
  public ModelView getEmpById(int id) {
    /// Your code here
  }
  ```

  <br> Champ correspondante :

  ```html
  <form action="get-emp-by-id.do" method="get">
  <input name="id" placeholder="ID employé"/>
  <button> Search </emp>
  </form>
  ```

## Upload

- Pour uploader un fichier , veuillez mettre comme attribut de votre classe l'objet `FileUpload` pour qu'il vérifie l'envoye d'un fichier si c'est présent durant l'opération.

- Exemple : <br>

```java
@Scope
public class Emp {

    FileUpload pictureEmp;

}
```

## Singleton

- Pour faire en sorte que votre classe soit un singleton ( il n'a qu'une seule instance ).

  - Veuillez annoter la classe avec l'annotation `Scope` ayant comme porté un `singleton`

  - Exemple : <br>

  ```java
    @Scope(types = "singleton")
    public class Emp {
    ...
    }
  ``
  ```

## Manipulation Session

### Ajout/Modifier session

- Pour ajouter ou modifier une/des session(s) vous devrez utiliser la fonction `setAttributeSession(String key, Object obj)` dans l'objet de type
  `ModelView` dans une fonction qui retoune une `ModelView`

Exemple : <br>

```java
@Url(value = "login.do")
  public ModelView login() {
      ModelView view = new ModelView();
      view.setAttributeSession("Profil", "admin");
      view.setAttributeSession("Name_user", "Peter");
      view.setUrl("index.jsp");
      return view;
  }
```


### Utiliser les sessions

- Pour pouvoir utiliser la valeur des session dans une méthode,ajouter un attribut hashmap de session et annoter le par l'annotation `SessionConfig`.

Exemple : <br>

```java
 @Scope
    public class Emp {

        String name;
        int age;
        @SessionConfig
        HashMap<String, Object> session;

        @Url(value = "emp-page.do")
        public ModelView getEmpPage() {
            Modelview mv = new Modelview("admin.jsp");
            mv.addItem("obj", session.get("obj"));
            return mv;
        }
    }
```

## Autorisation

- Pour donner à une méthode une autorisation requise pour pouvoir l'invoker utiliser l'annotation `Auth`
  qui s'appliquer qu'au méthode et qui prendra en argument dans l'attribut `profil` qui prendra en argument
  la valeur du profil accepté pour invoker la méthode.

Exemple :

```java

@Auth(profil = { "admin" })
  @Method(urlTo = "get-emp-by-id.do")
  public ModelView getEmpById(int id, String test) {
      ModelView view = new ModelView();
      Emp emp1 = new Emp("John " + test, id, 3440.0);
      Emp emp2 = new Emp("Jean " + test, id, 4440.0);
      Emp emp3 = new Emp("Patrick " + test, id, 6440.0);
      ...
```

- Ici le profil requis pour invoker la méthode `getEmpById` est un profil de "admin" .

- Vous devez aussi configurer votre `web.xml` pour pouvoir ajouter une méthode d'autorisation à une méthode .
  Exemple :

```xml
      <init-param>
          <param-name>session_name</param-name>
          <param-value>Profil</param-value>
          <description>Name of the session for authentification</description>
      </init-param>
```

- Vous pourer modifier la valeur `param-value` pour arranger votre gestion du nom de la session à regarder
  pour l'autorisation requise pour accéder à une méthode annoter .
- La valeur du `param-value` est le nom de la session qui va être verifier pour regarder si le client
  qui appel la méthode à l'autorisation nécessaire.

  ## Données en Json

  ### Données du ModelView en Json

  - Pour transformer les données d'une méthode qui retourne un `ModelView` en format Json utiliser la fonction
    `setJson(boolean isjson)` et entrer la valeur `true`.

  Exemple : <br>

  ```java
  @Method(urlTo = "view-to-json.do")
    public ModelView viewJson() {
        ModelView view = new ModelView();
        view.addItem("test", "hello World");
        view.addItem("name", "John Doe");
        view.setJson(true);
        return view;
    }
  ```


# Installation

- Utiliser le fichier go.bat après l'avoir configurer :

  - Changer la valeur de `rootPath` par le path dossier où vous travaillez

  - Changer la valeur de `warFileName` par le nom du fichier.war que vous voulez pour votre projet

  - Changer la valeur de `jspFilePath` par le path où se trouver vos fichier.jsp et les fichiers qui vont avec (css,js,...etc)

  - Changer la valeur de `sourceProjectPath` par le path où se trouve vos fichiers sources

  - Changer la valeur de `tempFilePath` par le path où vous voulez que le fichier temporaire se situe

  - Changer la valeur de `xmlConfigFile` par le path vers votre web.xml
