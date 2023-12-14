function Validator(options) {

    function getParentElement(element, selector) {
        while (element.parentElement) {
            if (element.parentElement.matches(selector)) {
                return element.parentElement;
            }
            element = element.parentElement;
        }
    }

    var selectRules = {};

    function validate(inputElement, rule) {
        var errorElement = getParentElement(inputElement, options.formGroupSelector).querySelector(options.errorSelector);
        var errorMessage;

        // get all rules of selector
        var tests = selectRules[rule.selector];

        //loop and check
        for (let i = 0; i < tests.length; i++) {
            switch (inputElement.type) {
                case 'radio':
                case 'checkbox':
                    errorMessage = tests[i](
                        formElement.querySelector(rule.selector + ':checked')
                    )
                    break;
                default:
                    errorMessage = tests[i](inputElement.value);
            }

            if (errorMessage) break;
        }

        if (errorMessage) {
            errorElement.innerText = errorMessage;
            getParentElement(inputElement, options.formGroupSelector).classList.add('invalid');
        } else {
            errorElement.innerText = '';
            getParentElement(inputElement, options.formGroupSelector).classList.remove('invalid');
        }

        return !errorMessage;
    }

    var formElement = document.querySelector(options.form);

    if (formElement) {

        formElement.onsubmit = (e) => {
            e.preventDefault();

            var isFormValid = true;

            // when submit, get all value in the form then validate
            options.rules.forEach(rule => {
                var inputElement = formElement.querySelector(rule.selector);

                var isValid = validate(inputElement, rule);
                if (!isValid) {
                    isFormValid = false;
                }
            });

            if (isFormValid) {
                // submit with js
                if (typeof options.onSubmit === 'function') {
                    var enableInputs = formElement.querySelectorAll('[name]');
                    var formValues = Array.from(enableInputs).reduce((values, input) => {

                        switch (input.type) {
                            case 'radio':
                                values[input.name] = formElement.querySelector('input[name="' + input.name + '"]:checked').value;
                                break;
                            case 'checkbox':
                                if (!input.matches(':checked')) {
                                    values[input.name] = [];
                                    return values;
                                }
                                if (!Array.isArray(values[input.name])) {
                                    values[input.name] = [];
                                }

                                values[input.name].push(input.value);
                                break;
                            case 'file':
                                values[input.name] = input.files;
                                break;
                            default:
                                values[input.name] = input.value;
                        }
                    }, {});
                }
            }
        }

        options.rules.forEach(rule => {

            // save rules for input
            if (Array.isArray(selectRules[rule.selector])) {
                selectRules[rule.selector].push(rule.test);
            } else {
                selectRules[rule.selector] = [rule.test];
            }

            var inputElements = formElement.querySelectorAll(rule.selector);

            Array.from(inputElements).forEach(inputElement => {
                if (inputElement) {
                    // when onblur
                    inputElement.onblur = () => {
                        validate(inputElement, rule);
                    }

                    // when oninput
                    inputElement.oninput = () => {
                        var parentForm = getParentElement(inputElement, options.formGroupSelector);
                        var errorElement = parentForm.querySelector(options.errorSelector);
                        errorElement.innerText = '';
                        parentForm.classList.remove('invalid');
                    }
                }
            })
        });
    }
}

// define rules
Validator.isRequired = (selector, message) => {
    return {
        selector: selector,
        test: (value) => {
            return value ? undefined : message || 'Vui lòng nhập trường này!';
        }
    };
}

Validator.isEmail = (selector, message) => {
    return {
        selector: selector,
        test: (value) => {
            var regex = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
            return regex.test(value) ? undefined : message || 'Trường này phải là email!'
        }
    };
}

Validator.minLength = (selector, min, message) => {
    return {
        selector: selector,
        test: (value) => {
            return value.length >= min ? undefined : message || `Mật khẩu phải nhiều hơn ${min} kí tự!`
        }
    };
}

Validator.isConfirmed = (selector, getConfirmValue, message) => {
    return {
        selector: selector,
        test: (value) => {
            return value === getConfirmValue() ? undefined : message || 'Giá trị nhập vào không chính xác!'
        }
    };
}