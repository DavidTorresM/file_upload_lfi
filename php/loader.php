<?php include 'header.php'; ?>

<div class="container">
    <h2>Contenido Dinámico</h2>
    <?php
    if (isset($_GET['page'])) {
        $page = $_GET['page'];
        include("pages/$page"); 
    } else {
        echo "<p>Selecciona una página válida.</p>";
    }
    ?>
</div>

<?php include 'footer.php'; ?>
