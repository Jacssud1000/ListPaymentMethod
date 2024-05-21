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
});