package ru.avem.modules.devices.delta.c2000

import ru.avem.library.polling.DeviceRegister
import ru.avem.library.polling.IDeviceModel

class C2000Model : IDeviceModel {
    companion object {
        const val ERRORS_REGISTER = "ERRORS_REGISTER"
        const val STATUS_REGISTER = "STATUS_REGISTER"
        const val CURRENT_FREQUENCY_INPUT_REGISTER = "CURRENT_FREQUENCY_INPUT_REGISTER"
        const val CONTROL_REGISTER = "CONTROL_REGISTER"
        const val CURRENT_FREQUENCY_OUTPUT_REGISTER = "CURRENT_FREQUENCY_OUTPUT_REGISTER"
        const val MAX_FREQUENCY_REGISTER = "MAX_FREQUENCY_REGISTER"
        const val NOM_FREQUENCY_REGISTER = "NOM_FREQUENCY_REGISTER"
        const val CURRENT_FREQUENCY_REGISTER = "CURRENT_FREQUENCY_REGISTER"
        const val MAX_VOLTAGE_REGISTER = "MAX_VOLTAGE_REGISTER"
        const val POINT_1_FREQUENCY_REGISTER = "POINT_1_FREQUENCY_REGISTER"
        const val POINT_1_VOLTAGE_REGISTER = "POINT_1_VOLTAGE_REGISTER"
        const val POINT_2_FREQUENCY_REGISTER = "POINT_2_FREQUENCY_REGISTER"
        const val POINT_2_VOLTAGE_REGISTER = "POINT_2_VOLTAGE_REGISTER"
        const val ENDS_STATUS_REGISTER = "ENDS_STATUS_REGISTER"
//      const val END_UP_CONTROL_REGISTER = "END_UP_CONTROL_REGISTER"
//      const val END_DOWN_CONTROL_REGISTER = "END_DOWN_CONTROL_REGISTER"
    }

    override val registers: Map<String, DeviceRegister> = mapOf(
        ERRORS_REGISTER to DeviceRegister(0x2100, DeviceRegister.RegisterValueType.SHORT),
        STATUS_REGISTER to DeviceRegister(0x2101, DeviceRegister.RegisterValueType.SHORT),
        CURRENT_FREQUENCY_INPUT_REGISTER to DeviceRegister(0x020C, DeviceRegister.RegisterValueType.SHORT),
        CONTROL_REGISTER to DeviceRegister(0x2000, DeviceRegister.RegisterValueType.SHORT),
        CURRENT_FREQUENCY_OUTPUT_REGISTER to DeviceRegister(0x2001, DeviceRegister.RegisterValueType.SHORT),
        MAX_FREQUENCY_REGISTER to DeviceRegister(0x0100, DeviceRegister.RegisterValueType.SHORT),
        NOM_FREQUENCY_REGISTER to DeviceRegister(0x0101, DeviceRegister.RegisterValueType.SHORT),
        MAX_VOLTAGE_REGISTER to DeviceRegister(0x0102, DeviceRegister.RegisterValueType.SHORT),
        POINT_1_FREQUENCY_REGISTER to DeviceRegister(0x0103, DeviceRegister.RegisterValueType.SHORT),
        POINT_1_VOLTAGE_REGISTER to DeviceRegister(0x0104, DeviceRegister.RegisterValueType.SHORT),
        POINT_2_FREQUENCY_REGISTER to DeviceRegister(0x0105, DeviceRegister.RegisterValueType.SHORT),
        POINT_2_VOLTAGE_REGISTER to DeviceRegister(0x0106, DeviceRegister.RegisterValueType.SHORT),
        ENDS_STATUS_REGISTER to DeviceRegister(0x041A, DeviceRegister.RegisterValueType.SHORT)
//      END_UP_CONTROL_REGISTER to DeviceRegister(0x0405, DeviceRegister.RegisterValueType.SHORT),
//      END_DOWN_CONTROL_REGISTER to DeviceRegister(0x0406, DeviceRegister.RegisterValueType.SHORT)
    )

    override fun getRegisterById(idRegister: String) =
        registers[idRegister] ?: error("Такого регистра нет в описанной карте $idRegister")
}
