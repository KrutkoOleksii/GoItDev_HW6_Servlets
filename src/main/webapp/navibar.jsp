<div class="navbar">
    <a href="${pageContext.request.contextPath}/">Home</a>
    <div class="dropdown">
        <button class="dropbtn">Company
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/company/">Show Companies</a>
            <a href="${pageContext.request.contextPath}/company/findCompany">Find Company</a>
            <a href="${pageContext.request.contextPath}/company/add">Create Company</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="dropbtn">Customer
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/customer/">Show Customers</a>
            <a href="${pageContext.request.contextPath}/customer/findCustomer">Find Customer</a>
            <a href="${pageContext.request.contextPath}/customer/add">Create Customer</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="dropbtn">Developer
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/developer/">Show Developers</a>
            <a href="${pageContext.request.contextPath}/developer/findDeveloper">Find Developer</a>
            <a href="${pageContext.request.contextPath}/developer/add">Create Developer</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="dropbtn">Project
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/project/">Show Projects</a>
            <a href="${pageContext.request.contextPath}/project/findProject">Find Project</a>
            <a href="${pageContext.request.contextPath}/project/add">Create Project</a>
        </div>
    </div>
    <div class="dropdown">
            <button class="dropbtn">Skill
                <i></i>
            </button>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/skill/">Show Skills</a>
                <a href="${pageContext.request.contextPath}/skill/findSkill">Find Skill</a>
                <a href="${pageContext.request.contextPath}/skill/add">Create Skill</a>
            </div>
        </div>
</div>