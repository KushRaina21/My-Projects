# Raina, Kush
# 1000-123-456
# 2018-09-07
# Assignment-01-02
import numpy as np
# This module calculates the activation function
def calculate_activation_function(weight,bias,input_array,type='Sigmoid'):
    net_value = weight * input_array + bias
    if type == 'Sigmoid':
        activation = 1.0 / (1 + np.exp(-net_value))
    elif type == "Linear":
        activation = net_value
    elif type == "Hyperbolic Tangent":
        activation = np.tanh(net_value)
    elif type == "RELU":
        activation = np.maximum(0,net_value)
    elif type == "Hard":
        print(net_value)
        temp1 = np.ma.masked_where(net_value < 0, net_value, copy=False)
        print(temp1)
        temp2 = temp1.filled(-1)
        print(temp2)
        temp3 = np.ma.masked_where(temp2 > 0, temp2, copy=False)
        activation = temp3.filled(1)
        print(activation)
    return activation