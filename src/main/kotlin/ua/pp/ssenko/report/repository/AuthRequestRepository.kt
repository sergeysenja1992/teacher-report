package ua.pp.ssenko.report.repository

import org.springframework.data.repository.CrudRepository
import ua.pp.ssenko.report.domain.AuthRequest

interface AuthRequestRepository: CrudRepository<AuthRequest, String> {

}
