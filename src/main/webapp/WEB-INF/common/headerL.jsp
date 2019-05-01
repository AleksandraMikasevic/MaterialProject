<nav class="navbar navbar-expand-lg navbar-light bg-light" style="background-color: #e3f2fd;">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" href="/MaterialReception/">Novkabel</a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <a class="nav-link" href="/MaterialReception/goods_received_note/all_goods_received_notes">Goods received note</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/MaterialReception/material/all_materials">Material</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/MaterialReception/supplier/all_suppliers">Supplier</a>
            </li>    
             <li class="nav-item">
                <a class="nav-link" href="/MaterialReception/weight_certificate/all_weight_certificate">Weight certificate</a>
            </li> 
            <li class="nav-item">
                <a class="nav-link" href="/MaterialReception/material/all_materials_graph">Graphics</a>
            </li>        
            <li class="nav-item">
                <a class="nav-link" href="/MaterialReception/find_employee"><%=session.getAttribute("employee")%></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/MaterialReception/logout">Sign out</a>
            </li>
        </ul>
    </div>
</nav>