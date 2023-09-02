        $(document).ready(function() {
            // DataTables initialization code here
            $('#yourTableId').DataTable();
        });

           // Function to switch content based on menu item click
        function switchContent(targetId) {
            const contentDivs = document.querySelectorAll('.orders, .products, .inquiry, .sms');
            contentDivs.forEach(div => {
                div.style.display = 'none';
            });

            const targetContent = document.getElementById(targetId);
            targetContent.style.display = 'block';
        }

        // Event listener for menu item clicks
        const menuItems = document.querySelectorAll('.list-group-item-action');

        menuItems.forEach(item => {
            item.addEventListener('click', function(event) {
                event.preventDefault();
                const targetId = this.getAttribute('href').substring(1); // Remove the '#'
                switchContent(targetId);
            });
        });

        window.addEventListener('DOMContentLoaded', event => {
            // Toggle the side navigation
            const sidebarToggle = document.body.querySelector('#sidebarToggle');
            if (sidebarToggle) {
                // Uncomment Below to persist sidebar toggle between refreshes
                // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
                //     document.body.classList.toggle('sb-sidenav-toggled');
                // }
                sidebarToggle.addEventListener('click', event => {
                    event.preventDefault();
                    document.body.classList.toggle('sb-sidenav-toggled');
                    localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
                });
            }
        });

        /**
        * Tooltips
        */
         var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
         var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
             return new bootstrap.Tooltip(tooltipTriggerEl)
         })