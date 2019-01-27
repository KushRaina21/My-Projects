# Raina, Kush
# 1001-567-809
# 2018-09-24
# Assignment-02-02
import numpy as np
import math

# This module calculates the activation function
def calculate_activation_function(weight,weight1,bias,input_array,type='Hyperbolic Tangent'):
    rows = input_array.shape[0]
    
    for x in range(0, rows):
        arry = np.array([-1,-1,1,1])
        print('array ',arry)
        net_value = weight * input_array[x,0] +weight1 * input_array[x,1]+ bias
        print('input array x',input_array[x,0] )
        print('input array y',input_array[x,1] )
        if type == "Linear":
            activation = net_value
        elif type == "Hyperbolic Tangent":
            activation = np.tanh(net_value)
            
        elif type == "Hard Limit":
            print(net_value)
            if(net_value>=0):
                activation=1
            else: 
                activation=-1
        print(activation)
        print('weight before activation ',weight)
        print('weight1 before activation ',weight1)
        print('expected ',arry[x])
        print('actual ',activation)
        
        error=arry[x]-activation
        
        print('error ',error)
        
        weight=weight+error*input_array[x,0]
        print('weight after correction ',weight)
        weight1=weight1+error*input_array[x,1]
        print('weight1 after correction  ',weight1)
        print('bias before correction  ',bias)
        bias=bias+ error
        print('bias after correction  ',bias)
        
    return weight,weight1,bias
        
       