document.addEventListener('DOMContentLoaded', (event) => {
    function fetchPaymentMethods(e) {
        e.preventDefault();
        fetch('http://localhost:8080/v1/payment_methods')
            .then(response => response.json())
            .then(data => {
                const paymentMethods = document.getElementById('paymentMethods');
                paymentMethods.innerHTML = '';
                data.forEach(method => {
                    const listItem = document.createElement('li');
                    listItem.innerHTML = `<strong>ID:</strong> ${method.id}<br>
                                         <strong>Nombre:</strong> ${method.name}<br>
                                         <strong>Método de Pago ID:</strong> ${method.payment_method_id}`;
                    paymentMethods.appendChild(listItem);
                });
            })
            .catch(error => console.error('Error al obtener la lista de métodos de pago:', error));
    }

    const button = document.getElementById("btList");
    button.addEventListener("click", fetchPaymentMethods);
});


