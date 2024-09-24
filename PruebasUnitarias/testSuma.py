from PruebasUnitarias import prueba


def test_suma():
    expected_result = 5
    actual_result = prueba.suma(2,3)
    assert expected_result == actual_result
    test_suma()