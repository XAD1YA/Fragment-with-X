package com.example.passwordx.database

import com.example.passwordx.model.Passport

interface PassportService {
    interface PassportService {
        fun savePassport(passport: Passport)
        fun updatePassport(passport: Passport)
        fun deletePassport(id: Int)
        fun getPassports(): List<Passport>
    }
}