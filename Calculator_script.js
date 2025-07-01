const display = document.querySelector('.calculator-display');
const numberButtons = document.querySelectorAll('[data-number]');
const operatorButtons = document.querySelectorAll('[data-operator]');
const equalsButton = document.querySelector('[data-equals]');
const clearButton = document.querySelector('[data-clear]');
const decimalButton = document.querySelector('[data-decimal]');

let currentOperand = '';   
let previousOperand = '';   
let operation = undefined; 

function appendNumber(number) {
    if (number === '0' && currentOperand === '0') return; 
    currentOperand = currentOperand.toString() + number.toString();
}

function appendDecimal() {
    if (currentOperand.includes('.')) return; 
    if (currentOperand === '') { 
        currentOperand = '0.';
    } else {
        currentOperand += '.';
    }
}

function chooseOperation(selectedOperation) {
    if (currentOperand === '') return; 
    if (previousOperand !== '') {
        calculate();
    }
    operation = selectedOperation;
    previousOperand = currentOperand;
    currentOperand = ''; 
}

function calculate() {
    let computation;
    const prev = parseFloat(previousOperand);
    const current = parseFloat(currentOperand);

    if (isNaN(prev) || isNaN(current)) return;

    switch (operation) {
        case '+':
            computation = prev + current;
            break;
        case '-':
            computation = prev - current;
            break;
        case '*':
            computation = prev * current;
            break;
        case '/':
            if (current === 0) {
                computation = 'Error: Div by 0';
                break;
            }
            computation = prev / current;
            break;
        default:
            return;
    }

    if (typeof computation === 'string' && computation.startsWith('Error')) {
        currentOperand = computation;
    } else {
        currentOperand = parseFloat(computation.toFixed(10)); 
    }

    operation = undefined;
    previousOperand = '';
}


function clear() {
    currentOperand = '0'; 
    previousOperand = '';
    operation = undefined;
}

function updateDisplay() {
    display.value = currentOperand === '' ? previousOperand : currentOperand;
    if (operation != null && currentOperand === '') {
        display.value = '{getDisplayNumber(previousOperand)}{operation}';
    } else {
        display.value = getDisplayNumber(currentOperand);
    }
}

function getDisplayNumber(number) {
    if (number === 'Error: Div by 0') return number; 

    const stringNumber = number.toString();
    const integerDigits = parseFloat(stringNumber.split('.')[0]);
    const decimalDigits = stringNumber.split('.')[1];
    let integerDisplay;

    if (isNaN(integerDigits)) {
        integerDisplay = '';
    } else {
        integerDisplay = integerDigits.toLocaleString('en', { maximumFractionDigits: 0 });
    }

    if (decimalDigits != null) {
        return '{integerDisplay}{decimalDigits}';
    } else {
        return integerDisplay;
    }
}


clear();
updateDisplay(); 

numberButtons.forEach(button => {
    button.addEventListener('click', () => {
        appendNumber(button.innerText);
        updateDisplay();
    });
});

operatorButtons.forEach(button => {
    button.addEventListener('click', () => {
        chooseOperation(button.innerText);
        updateDisplay();
    });
});

equalsButton.addEventListener('click', button => {
    calculate();
    updateDisplay();
});

clearButton.addEventListener('click', button => {
    clear();
    updateDisplay();
});

decimalButton.addEventListener('click', () => {
    appendDecimal();
    updateDisplay();
});

document.addEventListener('keydown', (e) => {
    if (e.key >= '0' && e.key <= '9') {
        appendNumber(e.key);
        updateDisplay();
    } else if (e.key === '.') {
        appendDecimal();
        updateDisplay();
    } else if (e.key === '+' || e.key === '-' || e.key === '*' || e.key === '/') {
        chooseOperation(e.key);
        updateDisplay();
    } else if (e.key === 'Enter' || e.key === '=') {
        calculate();
        updateDisplay();
    } else if (e.key === 'Escape' || e.key === 'c' || e.key === 'C') {
        clear();
        updateDisplay();
    } else if (e.key === 'Backspace'){
        performBackspace();
    }
    });