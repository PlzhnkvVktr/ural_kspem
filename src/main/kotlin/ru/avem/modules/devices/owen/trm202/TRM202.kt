package ru.avem.modules.devices.owen.trm202

import ru.avem.kserialpooler.adapters.modbusrtu.ModbusRTUAdapter
import ru.avem.kserialpooler.adapters.utils.ModbusRegister
import ru.avem.kserialpooler.utils.TransportException
import ru.avem.library.polling.DeviceController
import ru.avem.library.polling.DeviceRegister
import java.nio.ByteBuffer

class TRM202(
    override val name: String,
    override val protocolAdapter: ModbusRTUAdapter,
    override val id: Byte
) : DeviceController() {
    private val model = TRM202Model()
    override var requestTotalCount = 0
    override var requestSuccessCount = 0
    override val pollingRegisters = mutableListOf<DeviceRegister>()

    override val writingRegisters = mutableListOf<Pair<DeviceRegister, Number>>()

    override fun readRegister(register: DeviceRegister) {
        isResponding = try {
            transactionWithAttempts {
                val modbusRegister =
                    protocolAdapter.readHoldingRegisters(id, register.address, 2).map(ModbusRegister::toShort)
                register.value =
                    ByteBuffer.allocate(4).putShort(modbusRegister.first()).putShort(modbusRegister.second())
                        .also { it.flip() }.float
            }
            true
        } catch (e: TransportException) {
            false
        }
    }

    override fun readAllRegisters() {
        model.registers.values.forEach {
            readRegister(it)
        }
    }

    override fun <T : Number> writeRegister(register: DeviceRegister, value: T) {

    }

    override fun writeRegisters(register: DeviceRegister, values: List<Short>) {
        val registers = values.map { ModbusRegister(it) }
        isResponding = try {
            transactionWithAttempts {
                protocolAdapter.presetMultipleRegisters(id, register.address, registers)
            }
            true
        } catch (e: TransportException) {
            false
        }
    }

    override fun writeRequest(request: String) {}

    override fun checkResponsibility() {
        model.registers.values.firstOrNull()?.let {
            readRegister(it)
        }
    }

    override fun getRegisterById(idRegister: String) = model.getRegisterById(idRegister)

    private fun <T> List<T>.second(): T {
        if (size < 2) throw NoSuchElementException("List invalid size.")
        return this[1]
    }
}
