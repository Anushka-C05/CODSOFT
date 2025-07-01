 document.addEventListener('DOMContentLoaded', () => {
    const header = document.querySelector('header');
    const mobileMenuButton = document.getElementById('mobile-menu-button');
    const mainNav = document.getElementById('main-nav');
    const hamburgerIcon = mobileMenuButton.querySelector('.hamburger-icon');

    mobileMenuButton.addEventListener('click', () => {
        mainNav.classList.toggle('hidden');
        mainNav.classList.toggle('flex');
        mainNav.classList.toggle('flex-col');
        mainNav.classList.toggle('absolute');
        mainNav.classList.toggle('top-16');
        mainNav.classList.toggle('left-0');
        mainNav.classList.toggle('w-full');
        mainNav.classList.toggle('bg-white');
        mainNav.classList.toggle('p-4');
        mainNav.classList.toggle('shadow-lg');
        mainNav.classList.toggle('items-center');
        mainNav.classList.toggle('space-y-4');
        mainNav.classList.toggle('md:relative');
        mainNav.classList.toggle('md:flex-row');
        mainNav.classList.toggle('md:space-x-8');
        mainNav.classList.toggle('md:p-0');
        mainNav.classList.toggle('md:shadow-none');
        mainNav.classList.toggle('md:bg-transparent');
        
        hamburgerIcon.classList.toggle('open');
    });

    mainNav.querySelectorAll('a').forEach(link => {
        link.addEventListener('click', () => {
            if (!mainNav.classList.contains('hidden') && window.innerWidth < 768) {
                mainNav.classList.add('hidden');
                mainNav.classList.remove('flex', 'flex-col', 'absolute', 'top-16', 'left-0', 'w-full', 'bg-white', 'p-4', 'shadow-lg', 'items-center', 'space-y-4');
                hamburgerIcon.classList.remove('open');
            }
        });
    });

    window.addEventListener('scroll', () => {
        if (window.scrollY > 50) {
            header.classList.add('header-scrolled');
        } else {
            header.classList.remove('header-scrolled');
        }
    });
});