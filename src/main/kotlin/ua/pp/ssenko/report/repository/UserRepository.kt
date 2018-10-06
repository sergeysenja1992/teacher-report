package ua.pp.ssenko.report.repository

import org.springframework.data.repository.CrudRepository
import ua.pp.ssenko.report.domain.User

interface UserRepository: CrudRepository<User, String> {

}
