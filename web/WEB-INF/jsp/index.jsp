
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        
          <script src="js/newjavascript.js" type="text/javascript"></script>
          <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> 
        <link href="public/css/index.css" rel="stylesheet" type="text/css"/>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>adopta a tu mejor amigo</title>
    </head>

    <body>
        <%@include file="views/caebezera.jsp" %>
        
        <div style="margin-top: 3rem;">
            <h4 style="color: #98FB98;
                       text-align: center;
                       padding: 1rem;
                       ">
                ADOPTA A UN AMIGO
            </h4>
            
            <img
                style="margin-left:20rem;
                height: 400px;
                width: 700px;"
                alt="hola"
                src="data:image/jpeg;base64,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"
                
                />
            <p style="text-align: center; 
               padding-top: 3rem;
               padding-left: 12rem;
               font-family: cursive;
               width: 70rem;">
                El Instituto de Protección y Bienestar Animal IDPYBA,
                informó que en el 2022 regresarán las adopción de animales a Bogotá,
                luego de las exitosas jornadas que se realizaran en 2021.
                Recuerda que si deseas adoptar una mascota podrás dirigirte a la Unidad
                de Cuidado Animal (UCA) donde hay varios que han sido víctimas de maltrato,
                rescatados por el Escuadrón Anticrueldad, o que son atendidos por Urgencias 
                Veterinarias, entre otros programas. 
                Ten presente que si tienes deseas finalizar el 2021 con un animal de compañía 
                que haya sido recuperado por el IDPYBA, puedes dirigirte en la semana excepto 
                días feriados, a la Unidad de Cuidado Animal, ubicada en la Carrera 106A # 67-02,
                barrio El Muelle, en el horario comprendido de 10 a.m. a 3 p.m. 
            </p>
            
            
        </div>
       
        
        <%@include file="views/footer.jsp" %>    
    </body>
</html>
