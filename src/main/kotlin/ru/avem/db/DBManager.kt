package ru.avem.db

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection


object DBManager {
    init {
        Database.connect("jdbc:sqlite:data.db", "org.sqlite.JDBC")
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE

        validateData()
    }

    private fun validateData() {
        transaction {
            SchemaUtils.create(TestItems, TestProtocols, Users)
        }
        if (getAllUsers().isEmpty()) {
            addUser("admin", "888", true)
        }
        if (getAllTI().isEmpty()) {
            addTI(
                name = "ВМЗ05-01(380)",
                scheme = true,
                specifiedU = "380",
                specifiedI = "98",
                specifiedRPM = "740",
                specifiedP = "45",
                specifiedIdleTime = "60",
                specifiedRunningTime = "90",
                specifiedMgrU = "1000",
                specifiedViuU = "1760",
                specifiedViuTime = "60"
            )
        }

    }



    fun getAllUsers(): List<User> {
        return transaction {
            User.all().toList()
        }
    }
    fun addUser(
        login: String,
        password: String,
        isAdmin: Boolean
    ) {

        transaction {
            User.new {
                this.login = login
                this.password = password
                this.isAdmin = isAdmin
            }
        }
    }

    fun getAllTI(): List<String> {
        return transaction {
            TestItem.all().map { it.name }
        }
    }
    fun getAllTI1(): List<TestItem> {
        return transaction {
            TestItem.all().toList()
        }
    }


    fun getAllProtocols(): List<TestProtocol> {
        return transaction {
            TestProtocol.all().reversed().toList()
        }
    }

    fun getTI(value: String) = transaction { TestItem.find { TestItems.name eq value }.first() }
    fun getProtocolById(protocol: TestProtocol) = transaction { TestProtocol.find { TestProtocols.id eq protocol.id }.first() }

    fun addTI(
        name: String,
        scheme: Boolean,
        specifiedU: String,
        specifiedI: String,
        specifiedRPM: String,
        specifiedP: String,
        specifiedIdleTime: String,
        specifiedRunningTime: String,
        specifiedMgrU: String,
        specifiedViuU: String,
        specifiedViuTime: String
    ) {
        transaction {
            TestItem.new {
                this.name = name
                this.scheme = scheme
                this.specifiedU = specifiedU
                this.specifiedI = specifiedI
                this.specifiedRPM = specifiedRPM
                this.specifiedP = specifiedP
                this.specifiedIdleTime = specifiedIdleTime
                this.specifiedRunningTime = specifiedRunningTime
                this.specifiedMgrU = specifiedMgrU
                this.specifiedViuU = specifiedViuU
                this.specifiedViuTime = specifiedViuTime
            }
        }
    }

    fun deleteTestItemById(name: String) {
        transaction {
            TestItems.deleteWhere {
                TestItems.name eq name
            }
        }
    }

    fun deleteProtocolItemByName1(item: TestProtocol) {
        transaction {
            TestProtocols.deleteWhere {
                TestProtocols.id eq item.id
            }
        }
    }
    fun deleteUser(user: User) {
        transaction {
            Users.deleteWhere {
                Users.id eq user.id
            }
        }
    }

    fun updateUser(user: User, newLogin: String, newPassword: String, isAdminStatus: Boolean) {
        transaction {
            Users.update ({ Users.id eq user.id }) {
                it[login] = newLogin
                it[password] = newPassword
                it[isAdmin] = isAdminStatus
            }
        }
    }


    fun findUser(login: String): List<User> {
        return getAllUsers().filter { it.login.contains(login) }
    }
    fun findUserLogin(login: String): Boolean {
        return getAllUsers().find { it.login == login} != null
    }
    fun findProtocol(itemName: String): List<TestProtocol> {
        return getAllProtocols().filter { it.name.contains(itemName) }
    }

    fun findCurrentProtocol(itemName: String): TestProtocol? {
        return getAllProtocols().find { it.name == itemName }
    }

    fun addNewProtocol(type: String, testItem: TestItem, name: String) {



    }
}

