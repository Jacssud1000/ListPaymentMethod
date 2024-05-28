document.addEventListener("DOMContentLoaded", function() {
    const mp = new MercadoPago("APP_USR-4f4253b6-023c-4eba-9323-bd055d36f1ce");

    async function getIdentificationTypes() {
        try {
            const identificationTypes = await mp.getIdentificationTypes();
            const identificationTypeElement =
                document.getElementById('form-checkout__identificationType');
            createSelectOptions(identificationTypeElement, identificationTypes);
        } catch (e) {
            return console.error('Error getting identificationTypes: ', e);
        }
    }

    async function submit(e) {
        e.preventDefault();
        const first_name = document.getElementById("form-checkout__payerFirstName").value;
        const last_name = document.getElementById("form-checkout__payerLastName").value;
        const email = document.getElementById("form-checkout__email").value;
        const type = document.getElementById("form-checkout__identificationType").value;
        const number = document.getElementById("form-checkout__identificationNumber").value;

        const producto = {
            transaction_amount: 100,
            description: "Titulo do produto",
            payment_method_id: "bolbradesco",
            payerDto: {
                email: email,
                first_name,
                last_name,
                identification: {
                    type,
                    number
                }
            }
        }

        try {
            const response = await fetch("http://localhost:8080/process_payment", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(producto)
            });

            if (!response.ok) {
                throw new Error('Error en la solicitud de pago');
            }

            const paymentData = await response.json();
            console.log(paymentData);

            if (paymentData.external_resource_url) {
                const externalResourceUrl = paymentData.external_resource_url;
                document.getElementById('boleto-link').href = externalResourceUrl;
                document.getElementById('payment-result').style.display = 'block';
            } else {
                console.error('external_resource_url no encontrado en la respuesta');
            }

        } catch (error) {
            console.error('Error al realizar el pago:', error);
        }
    }

    function createSelectOptions(elem, options, labelsAndKeys =
        {label: "name", value: "id"}) {
        const {label, value} = labelsAndKeys;

        elem.options.length = 0;

        const tempOptions = document.createDocumentFragment();

        options.forEach(option => {
            const optValue = option[value];
            const optLabel = option[label];

            const opt = document.createElement('option');
            opt.value = optValue;
            opt.textContent = optLabel;

            tempOptions.appendChild(opt);
        });

        elem.appendChild(tempOptions);
    }

    getIdentificationTypes();

    const button = document.getElementById("btPayment");

    button.addEventListener("click", submit);
});