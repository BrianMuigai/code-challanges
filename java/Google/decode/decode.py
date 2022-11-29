import base64

MESSAGE = '''
GVUaFA0OEBoURkkLQlUOEwsMAU5LQU5SDR4FBA8KAAxAQVMRRRcaFQsIGAwDRkURRRcPBwEfARpA QVMRRRsHAhwIEQAFDQwWTlJOAA0FHAwRBARUDAZOQVRNUhwJDQZSCRcNRkJNUhsGAwtYFgFOQVRN UhoGBwwWTlJOBwECUkldQU5GCxxIRhM=
'''

KEY = 'brianmuigai1'

result = []
for i, c in enumerate(base64.b64decode(MESSAGE)):
    result.append(chr(c ^ ord(KEY[i % len(KEY)])))

print(''.join(result))
